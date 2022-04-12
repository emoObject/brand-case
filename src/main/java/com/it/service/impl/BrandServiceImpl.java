package com.it.service.impl;

import com.it.mapper.BrandMapper;
import com.it.pojo.Brand;
import com.it.pojo.PageBean;
import com.it.service.BrandService;
import com.it.util.SqlUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    SqlSessionFactory factory = SqlUtils.getSqlSessionFactory();

    @Override
    public List<Brand> selectAll() {
        //1 获得sqlSession执行语句
        SqlSession sqlSession = factory.openSession();

        //2 获得代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //3 查询所有
        List<Brand> brands = mapper.selectAll();

        //4 释放资源
        sqlSession.close();

        return brands;
    }

    @Override
    public void add(Brand brand) {
        //1 获得sqlSession执行语句
        SqlSession sqlSession = factory.openSession();

        //2 获得代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //3 添加数据
        mapper.add(brand);

        //4 提交事务
        sqlSession.commit();

        //5 释放资源
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        //1 获得sqlSession执行语句
        SqlSession sqlSession = factory.openSession();

        //2 获得代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //3 调用方法
        mapper.deleteByIds(ids);
        //4 提交事务
        sqlSession.commit();

        //5 释放资源
        sqlSession.close();
    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        //1 获得sqlSession执行语句
        SqlSession sqlSession = factory.openSession();

        //2 获得代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //3 计算开始索引
        int begin = (currentPage - 1) * pageSize;

        //计算查询条目数
        int size = pageSize;

        //4查询当前页数据
        List<Brand> rows = mapper.selectByPage(begin, size);

        //5查询总记录数
        int totalCount = mapper.selectTotalCount();

        //6封装PageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //8释放资源
        sqlSession.close();
        return pageBean;
    }

    @Override
    public PageBean<Brand> selectPageAndCondition(int currentPage, int pageSize, Brand brand) {
        //1 获得sqlSession执行语句
        SqlSession sqlSession = factory.openSession();

        //2 获得代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //3 计算开始索引
        int begin = (currentPage - 1) * pageSize;

        //计算查询条目数
        int size = pageSize;

        //处理brand条件，模糊表达式
        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0) {
            brand.setBrandName("%" + brandName + "%");
        }

        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() > 0) {
            brand.setCompanyName("%" + companyName + "%");
        }

        //5 查询当前页数据
        List<Brand> rows = mapper.selectByPageAndCondition(begin, size, brand);

        //6 查询总记录数据
        int totalCount = mapper.selectTotalCountByCondition(brand);

        //7 封装PageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //8 释放资源
        sqlSession.close();

        return pageBean;
    }
}
