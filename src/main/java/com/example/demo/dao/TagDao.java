package com.example.demo.dao;

import com.example.demo.entity.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author lichengyang
 * @Date 2020/5/22 15:42
 */
@Repository
public interface TagDao {

    Tag getById(@Param("id")int id);

    /**
     * @description 根据层级查找
     * @author lichengyang
     * @date 2020/5/22 18:06
    */
    List<Tag> getLisyByHierarchy(@Param("hierarchy")int hierarchy);

    /**
     * @description 根据父级标签查找
     * @author lichengyang
     * @date 2020/5/22 18:06
    */
    List<Tag> getLisyByParentId(@Param("parentId")int parentId);

    int insert(Tag tag);

}
