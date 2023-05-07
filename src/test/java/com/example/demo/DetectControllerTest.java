package com.example.demo;

import com.example.demo.controller.DetectController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

@SpringBootTest
class DetectControllerTest {
    DetectController detectController;


    @Autowired
    public void setDetectController(DetectController detectController) {
        this.detectController = detectController;
    }

    @Test
    void detect() {
        HttpServletRequest request = new MockHttpServletRequest();
        request.setAttribute("path","www.heguchangan.xyz");
        detectController.detect(request);
    }
}

