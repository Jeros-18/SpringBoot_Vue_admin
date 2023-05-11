package com.example.demo.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.entity.Camera;
import com.example.demo.entity.Files;
import com.example.demo.entity.Img;
import com.example.demo.mapper.CameraMapper;
import com.example.demo.mapper.FileMapper;
import com.example.demo.mapper.ImgMapper;
import com.example.demo.service.ICameraService;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.security.Security;
import java.util.List;

/**
 * 文件上传相关接口
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${files.upload.path}")
    private String fileUploadPath;
    @Value("${files.upload.path2}")
    private String fileUploadPath2;
    @Value("${files.upload.detect1Path}")
    private String detect1Path;
    @Value("${files.upload.detect2Path}")
    private String detect2Path;
    @Value("${files.upload.recognisePath}")
    private String recognisePath;

    String yearReco = "1619";

    @Value("${server.ip}")
    private String serverIp;

    @Resource
    private FileMapper fileMapper;

    @Resource
    private ImgMapper imgMapper;

    @Resource
    private CameraMapper cameraMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private ICameraService cameraService;


    /**
     * 文件上传接口
     * @param file 前端传递过来的文件
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();

        // 定义一个文件唯一的标识码
        String fileUUID = IdUtil.fastSimpleUUID() + StrUtil.DOT + type;

        File uploadFile = new File(fileUploadPath + fileUUID);
        // 判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        File parentFile = uploadFile.getParentFile();
        if(!parentFile.exists()) {
            parentFile.mkdirs();
        }

        String url;
        // 获取文件的md5
        String md5 = SecureUtil.md5(file.getInputStream());
        // 从数据库查询是否存在相同的记录
        Files dbFiles = getFileByMd5(md5);
        if (dbFiles != null) {
            url = dbFiles.getUrl();
        } else {
            // 上传文件到磁盘
            file.transferTo(uploadFile);
            // 数据库若不存在重复文件，则不删除刚才上传的文件
            url = "http://" + serverIp + ":9090/file/" + fileUUID;
        }


        // 存储数据库
        Files saveFile = new Files();
        saveFile.setName(originalFilename);
        saveFile.setType(type);
        saveFile.setSize(size/1024); // 单位 kb
        saveFile.setUrl(url);
        saveFile.setMd5(md5);
        fileMapper.insert(saveFile);

        // 从redis取出数据，操作完，再设置（不用查询数据库）
//        String json = stringRedisTemplate.opsForValue().get(Constants.FILES_KEY);
//        List<Files> files1 = JSONUtil.toBean(json, new TypeReference<List<Files>>() {
//        }, true);
//        files1.add(saveFile);
//        setCache(Constants.FILES_KEY, JSONUtil.toJsonStr(files1));


        // 从数据库查出数据
//        List<Files> files = fileMapper.selectList(null);
//        // 设置最新的缓存
//        setCache(Constants.FILES_KEY, JSONUtil.toJsonStr(files));

        // 最简单的方式：直接清空缓存
//        flushRedis(Constants.FILES_KEY);

        return url;
    }

//    对3568上传的图片进行识别
    @GetMapping("/upload3")
    public Result upload3(){

        String kuangUrl = null;
        String recogUrl = null;
        String url;


//       对 D:/aprograme1/finalDoc/img2中的所有图片进行目标检测，结果存到temp/exp中
        usePython("D:\\PycharmProjects\\yolov5-6.0\\detect2.py");

//        对temp/exp中的图片-裁剪,保存到temp/kuang
        usePython("D:\\PycharmProjects\\PaddleOCR\\cai2.py");


//        对 D:/aprograme1/finalDoc/img2中的所有图片,遍历进行ocr识别（temp/result）+信息存入数据库
        String dirName="D:\\aprograme1\\finalDoc\\img2";
        File file = new File(dirName);
        if (file.isDirectory()) {
            String[] list = file.list();
            for (int i=0; i<list.length;i++){



                System.out.println(list[i]); // list[i] 图片名
                //        识别-pic_extractedCamera中的图片=裁剪区域
                usePythonParameter("D:\\PycharmProjects\\PaddleOCR\\test3.py",list[i]);

                url="http://" + serverIp + ":9090/tire/edge/" + list[i];
                kuangUrl="http://" + serverIp + ":9090/exp/" + list[i];
                recogUrl="http://" + serverIp + ":9090/result/" + list[i];

                Camera camera = new Camera();
                camera.setName(list[i]);
                camera.setYuan(url);
                camera.setKuang(kuangUrl);
                camera.setRecog(recogUrl);
                camera.setYear(yearReco);
                cameraService.saveOrUpdate(camera);
            }
        }


        //        移动temp/exp,kuang ,result   ->  exp,kuang,result
        usePython("D:\\PycharmProjects\\PaddleOCR\\delete.py");
        //        删除temp/exp
        usePython("D:\\PycharmProjects\\PaddleOCR\\delete0.py");



        return Result.success();
    }




    @PostMapping("/upload2")
    public String upload2(@RequestParam MultipartFile file,HttpServletRequest request) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();

        // 定义一个文件唯一的标识码
//        String fileUUID = IdUtil.fastSimpleUUID() + StrUtil.DOT + type;

        File uploadFile = new File(fileUploadPath2 + originalFilename);
        // 判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        File parentFile = uploadFile.getParentFile();
        if(!parentFile.exists()) {
            parentFile.mkdirs();
        }

        String kuangUrl = null;
        String recogUrl = null;


        String url;
        int i=1;

//        kuangUrl="http://" + serverIp + ":9090/exp"+(i++)+"/" + fileUUID;
        // 上传文件到磁盘



        // 上传文件到磁盘
        file.transferTo(uploadFile);
        // 数据库若不存在重复文件，则不删除刚才上传的文件
//        url = "http://" + serverIp + ":9090/file/" + originalFilename;


        url="http://" + serverIp + ":9090/tire/" + originalFilename;
        kuangUrl="http://" + serverIp + ":9090/exp/" + originalFilename;
        recogUrl="http://" + serverIp + ":9090/result/" + originalFilename;








//       对 D:/files/temp/tire中的所有图片进行目标检测，结果存到expCamera中
        usePython("D:\\PycharmProjects\\yolov5-6.0\\detect.py");

//        对expCamera中的图片-裁剪
        usePython("D:\\PycharmProjects\\PaddleOCR\\cai2.py");

//        识别-pic_extractedCamera中的图片=裁剪区域
//        usePython("D:\\PycharmProjects\\PaddleOCR\\test3.py");
        usePythonParameter("D:\\PycharmProjects\\PaddleOCR\\test3.py",originalFilename);

        //        移动cai/kuang result
        usePython("D:\\PycharmProjects\\PaddleOCR\\delete.py");
        //        删除exp +
        usePython("D:\\PycharmProjects\\PaddleOCR\\delete0.py");

        usePython("D:\\PycharmProjects\\PaddleOCR\\deleteTire.py");

// 存储临时表，为了显示检测和识别效果
        Img img = new Img();
        img.setImgname(originalFilename);
        img.setYuan(url);
        img.setKuang(kuangUrl);
        img.setRecog(recogUrl);
        img.setRyear(yearReco);
        imgMapper.insert(img);

        return url;
    }



    // 删除
    public void  usePython(String path){


        try {
            // 一维数组，第二个参数是文件的路径，后面的是python代码的参数
            // 我猜是通过命令行指令？
            String[] args = new String[]{"python",path};
            // 执行py文件
            Process process = Runtime.getRuntime().exec(args);
            // 获取输出的结果（打印在控制台的字符？）
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = in.readLine();
            while(line!=null){
                // 显示结果
                System.out.println("springboot执行python结果:"+line);
                line = in.readLine();
            }
            in.close();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(path+"任务已完成");
    }

    // 删除
    public void usePythonParameter(String path,String parameter){


        try {
            // 一维数组，第二个参数是文件的路径，后面的是python代码的参数
            // 我猜是通过命令行指令？
            String[] args = new String[]{"python",path,parameter};
            // 执行py文件
            Process process = Runtime.getRuntime().exec(args);
            // 获取输出的结果（打印在控制台的字符？）
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = in.readLine();

            while(line!=null){
                // 显示结果
                System.out.println("springboot执行python结果:"+line);
                line = in.readLine();
                if(line!=null){
                yearReco = line;}
            }


            in.close();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(path+"任务已完成"+yearReco+"yearReco数据类型："+yearReco.getClass().toString());


    }




    /**
     * 文件下载接口   http://localhost:9090/file/{fileUUID}
     * @param fileUUID
     * @param response
     * @throws IOException
     */
    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        // 根据文件的唯一标识码获取文件
        File uploadFile = new File(fileUploadPath + fileUUID);
        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");

        // 读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }


    /**
     * 通过文件的md5查询文件
     * @param md5
     * @return
     */
    private Files getFileByMd5(String md5) {
        // 查询文件的md5是否存在
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        List<Files> filesList = fileMapper.selectList(queryWrapper);
        return filesList.size() == 0 ? null : filesList.get(0);
    }

//    @CachePut(value = "files", key = "'frontAll'")
    @PostMapping("/update")
    public Result update(@RequestBody Files files) {
        fileMapper.updateById(files);
        flushRedis(Constants.FILES_KEY);
        return Result.success();
    }

    @GetMapping("/detail/{id}")
    public Result getById(@PathVariable Integer id) {
        return Result.success(fileMapper.selectById(id));
    }

    //清除一条缓存，key为要清空的数据
//    @CacheEvict(value="files",key="'frontAll'")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Files files = fileMapper.selectById(id);
        files.setIsDelete(true);
        fileMapper.updateById(files);
        flushRedis(Constants.FILES_KEY);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        // select * from sys_file where id in (id,id,id...)
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        List<Files> files = fileMapper.selectList(queryWrapper);
        for (Files file : files) {
            file.setIsDelete(true);
            fileMapper.updateById(file);
        }
        return Result.success();
    }

    /**
     * 分页查询接口
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name) {

        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        // 查询未删除的记录
        queryWrapper.eq("is_delete", false);
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        return Result.success(fileMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper));
    }

    // 设置缓存
    private void setCache(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    // 删除缓存
    private void flushRedis(String key) {
        stringRedisTemplate.delete(key);
    }

}
