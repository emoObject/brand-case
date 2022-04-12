package com.it.service;

import com.it.pojo.Brand;
import com.it.pojo.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandService {
    //查询数据
    List<Brand>selectAll();

    //添加数据
    void  add(Brand brand);

    //批量删除
    void deleteByIds(int[] ids);

    //分页查询
    PageBean<Brand>selectByPage(int currentPage,int pageSize);

    //分页条件查询
    PageBean<Brand>selectPageAndCondition(int currentPage,int pageSize,Brand brand);
}
