package com.example.demo.entity;

import lombok.Data;

/**
 * @Description 标签类
 * @Author lichengyang
 * @Date 2020/5/22 15:12
 */
@Data
public class Tag {
    //id
    private Integer id;
    //层级关系
    private Integer hierarchy;
    //上级id
    private Integer parentId;
    //内容
    private String content;
    //标签类型 0-普通标签 1-超集/子集标签 2-互斥标签
    private Integer type;
    //是否删除 0-正常 1-删除
    private Integer del;
}
