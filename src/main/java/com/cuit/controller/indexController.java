package com.cuit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : CLEAR Li
 * @version : V1.0
 * @className : indexController
 * @packageName : com.cuit.controller
 * @description : 一般类
 * @date : 2020-06-29 9:17
 **/
@Controller
public class indexController {
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("content","prematch");
        return "index";
    }
}
