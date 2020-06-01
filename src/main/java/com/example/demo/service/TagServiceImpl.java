package com.example.demo.service;

import com.example.demo.dao.TagDao;
import com.example.demo.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author lichengyang
 * @Date 2020/5/22 15:54
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    @Override
    public Object getParents(HttpServletRequest request) {
        String tagId = request.getParameter("tagId");
        List<Tag> list = new ArrayList<>();

        Tag tag = tagDao.getById(Integer.parseInt(tagId));
        //没有找到该标签
        if (tag == null) {
            return "Tag not found!";
        }
        if (tag.getHierarchy() == 0) {
            //如果是顶级标签
            return tagDao.getLisyByHierarchy(0);
        } else {
            list.add(tagDao.getById(tag.getParentId()));
            list.addAll(tagDao.getLisyByParentId(tag.getParentId()));
        }
        return list;
    }


    @Override
    public Object getLength(HttpServletRequest request) {
        String tagId1 = request.getParameter("tagId1");
        String tagId2 = request.getParameter("tagId2");
        Tag tag1 = tagDao.getById(Integer.parseInt(tagId1));
        Tag tag2 = tagDao.getById(Integer.parseInt(tagId2));

        if (tag1 == null || tag2 == null) {
            return "Tag not found!";
        }

        //获取父标签
        if (tag1.getParentId().equals(tag2.getParentId())) {
            //有相同的父标签
            switch (tagDao.getById(tag1.getParentId()).getType()) {
                case 0:
                    return "1";
                case 1:
                    return "0";
                case 2:
                    return "∞";
                default:
                    return "";
            }
        } else {
            //先找到公共祖先
            List<Tag> parent1 = getAllParents(tag1, new ArrayList<>());
            List<Tag> parent2 = getAllParents(tag2, new ArrayList<>());
            Tag publicParent = null;
            for (int i = parent1.size() - 1, j = parent2.size() - 1; i >= 0 || j >= 0; i--, j--) {
                if (parent1.get(i).getId().equals(parent2.get(j).getId())) {
                    publicParent = parent1.get(i);
                } else {
                    break;
                }
            }
            if (null == publicParent) {
                return "Two tags have no same parent!";
            }
            return tag1.getHierarchy() + tag2.getHierarchy() - publicParent.getHierarchy() * 2 - 1;
        }
    }


    @Override
    public Object importTags(HttpServletRequest request) {
        String content = request.getParameter("content");
        String type = request.getParameter("type");
        String parentId = request.getParameter("parentId");
        if (content == null || type == null) {
            return "Content or type cannot be empty!";
        }
        int typeInt;
        try {
            typeInt = Integer.parseInt(type);
        } catch (Exception e) {
            return "type error!";
        }
        if (typeInt != 0 && typeInt != 1 && typeInt != 2) {
            return "type must be 0、1 or 2 ";
        }
        Tag tag = new Tag();
        tag.setContent(content);
        tag.setType(Integer.parseInt(type));
        if (parentId == null) {
            tag.setHierarchy(0);
        } else {
            Tag parent = tagDao.getById(Integer.parseInt(parentId));
            if (null == parent) {
                return "Parent is not exist!";
            }
            tag.setHierarchy(parent.getHierarchy() + 1);
            tag.setParentId(parent.getId());
        }
        tagDao.insert(tag);
        return "insert sucess ! id is " + tag.getId();
    }

    //递归查找一个节点的所有祖先
    private List<Tag> getAllParents(Tag tag, List<Tag> result) {
        result.add(tag);
        if (tag.getHierarchy() == 0) {
            return result;
        }

        Tag parent = tagDao.getById(tag.getParentId());
        getAllParents(parent, result);

        return result;
    }

}
