package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
public class DetectController {

    @GetMapping
    public void  detect(HttpServletRequest request){
        String path = request.getAttribute("path").toString();

        try {
            // 一维数组，第二个参数是文件的路径，后面的是python代码的参数
            // 我猜是通过命令行指令？
            String[] args = new String[]{"python","D:\\PycharmProjects\\yolov5\\myWeight\\cai.py",path};
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


        System.out.println("controller任务已完成");
    }

}


