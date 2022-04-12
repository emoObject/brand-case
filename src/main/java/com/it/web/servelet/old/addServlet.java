package com.it.web.servelet.old;

import com.alibaba.fastjson.JSON;
import com.it.pojo.Brand;
import com.it.service.BrandService;
import com.it.service.impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

//@WebServlet("/add")
public class addServlet extends HttpServlet {
    private BrandService service=new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 接收品牌数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();//json字符串
        //转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
        //2 调用service添加数据
        service.add(brand);
        //3 响应成功的标识
        response.getWriter().write("success");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
