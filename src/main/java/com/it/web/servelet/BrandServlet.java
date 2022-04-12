package com.it.web.servelet;

import com.alibaba.fastjson.JSON;
import com.it.pojo.Brand;
import com.it.pojo.PageBean;
import com.it.service.BrandService;
import com.it.service.impl.BrandServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private BrandService service=new BrandServiceImpl();
    //查询
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1 调用service查询
        List<Brand> brands = service.selectAll();

        //2 转为JSON
        String jsonString = JSON.toJSONString(brands);

        //3 写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    //添加
    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1 接收品牌数据
        BufferedReader reader = req.getReader();
        String params = reader.readLine();//json字符串
        //转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
        //2 调用service添加数据
        service.add(brand);
        //3 响应成功的标识
        resp.getWriter().write("success");
    }

    //批量删除
    public void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1 接收数据[1,2,3]
        BufferedReader reader = req.getReader();
        String params = reader.readLine();//json字符串
        //转为 int[]
        int[] ids = JSON.parseObject(params, int[].class);
        //2 调用service添加
        service.deleteByIds(ids);
        //3 响应成功的标识
        resp.getWriter().write("success");
    }

    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws
            IOException {
        //1. 接收 当前页码 和 每页展示条数 url?currentPage=1&pageSize=5
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //2 调用service查询
        PageBean<Brand> pageBean = service.selectByPage(currentPage, pageSize);

        //3 转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //4 写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void selectByPageAndCondition(HttpServletRequest req, HttpServletResponse resp) throws
            IOException {
        //1 接收 当前页码 和 每页展示条数 url?currentPage=1&pageSize=5
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //获取查询条件对象
        BufferedReader br = req.getReader();
        String params = br.readLine();//json字符串

        //转为Brand
        Brand brand = JSON.parseObject(params, Brand.class);

        //2 调用service查询
        PageBean<Brand> pageBean = service.selectPageAndCondition(currentPage, pageSize, brand);

        //转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    //修改
    public void update(HttpServletRequest req, HttpServletResponse resp) {}
    //删除
    public void delete(HttpServletRequest req, HttpServletResponse resp) {}

}
