package com.it.web.servelet.old;

import com.alibaba.fastjson.JSON;
import com.it.pojo.Brand;
import com.it.service.BrandService;
import com.it.service.impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

//@WebServlet("/selectAll")
public class SelectAllServlet extends HttpServlet {
    private BrandService service=new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 调用service查询
        List<Brand> brands = service.selectAll();

        //2 转为JSON
        String jsonString = JSON.toJSONString(brands);

        //3 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
