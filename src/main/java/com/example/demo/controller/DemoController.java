package com.example.demo.controller;

import com.example.demo.service.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author lichengyang
 * @Date 2020/5/22 15:33
 */
@RestController
@RequestMapping("/tags")
public class DemoController {
    @Autowired
    private TagServiceImpl tagService;

    @RequestMapping("/get")
    private Object getxx(HttpServletRequest request) {
        try {
            String method = request.getParameter("method");
            switch (method){
                case "getParents":
                    return tagService.getParents(request);
                case "getLength":
                    return tagService.getLength(request);
                case "importTags":
                    return tagService.importTags(request);
                default:
                    return "Method error!";
            }
        } catch (Exception e) {
            return "Requrest error, please try again!";
        }
    }

}
