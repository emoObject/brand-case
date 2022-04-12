package com.it.mapper;

import com.it.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {
    //查询所有
    @Select("select *from mybatis.tb_brand")
    @ResultMap("brandResultMap")
    List<Brand>selectAll();

    //添加
    @Insert("insert into mybatis.tb_brand values (null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);

    //批量删除
    void deleteByIds(@Param("ids")int[] ids);

    //分页查询
    @Select("select *from mybatis.tb_brand limit #{begin},#{size}")
    @ResultMap("brandResultMap")
    List<Brand>selectByPage(@Param("begin")int begin,@Param("size")int size);

    //统计记录数
    @Select("select count(*) from mybatis.tb_brand")
    int selectTotalCount();

    //条件分页查询功能
    List<Brand> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int size,@Param("brand") Brand brand);

    //(建议通过mapper类去创建插件)
    //根据条件查询总记录数
    int selectTotalCountByCondition(Brand brand);
}
