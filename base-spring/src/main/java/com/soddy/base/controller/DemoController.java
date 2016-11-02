package com.soddy.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by soddygo on 2016/11/2.
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/test1.html")
    public String testDemo(){
        return "index";
    }
}
