package com.cuit.controller;

import com.cuit.dto.PageBeanDTO;
import com.cuit.mapper.Temp;
import com.cuit.result.Result;
import com.cuit.service.SparkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private  SparkService sparkService;
    @Autowired
    public void setSparkService(SparkService sparkService) {
        this.sparkService = sparkService;
    }

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("content","prematch");
        return "index";
    }
    @RequestMapping(value = "/spark",method = RequestMethod.POST)
    @ResponseBody
    public Object SparkSearch(String content,String type){
        System.out.println(content+type);
        sparkService.query(content,Integer.valueOf(type));
        return  new Result<String>();
    }
    @ResponseBody
    @RequestMapping("byPage")
    public Result<PageBeanDTO<Temp>> byPage(int currentPage, int limit){
        PageBeanDTO<Temp> pageBeanDTO = new PageBeanDTO<>();
        pageBeanDTO.setCurrentPage(currentPage);
        pageBeanDTO.setPageSize(limit);
        PageBeanDTO<Temp> commentPageBeanDTO = sparkService.getComment(pageBeanDTO);
        return new Result<>(commentPageBeanDTO);
    }
}
