package com.example.demo.service;


import javax.servlet.http.HttpServletRequest;

public interface TagService {
    /**
     * @description 获取某个标签的所有父级标签
     * @author lichengyang
     * @date 2020/5/22 17:04
    */
    Object getParents(HttpServletRequest request);

    /**
     * @description 获取两个标签之间的距离
     * @author lichengyang
     * @date 2020/5/22 17:04
    */
    Object getLength(HttpServletRequest request);

    /**
     * @description 导入标签组
     * @author lichengyang
     * @date 2020/5/22 17:05
    */
    Object importTags(HttpServletRequest request);

}
