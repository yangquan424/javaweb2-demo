package com.cheer.web.servlet;

import com.cheer.mybatis.model.Chengji;
import com.cheer.mybatis.model.Exam;
import com.cheer.mybatis.service.ChengjiService;
import com.cheer.mybatis.service.ExamService;
import com.cheer.mybatis.service.impl.ChengjiServiceImpl;
import com.cheer.mybatis.service.impl.ExamServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Student_resultServlet", urlPatterns = "/servlet/Student_resultServlet")
public class Student_resultServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        //获得考生的ID，相当于准考号
        int stuId = (Integer) session.getAttribute("userId");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();

        ChengjiService chengji = new ChengjiServiceImpl();
        List<Chengji> empList = chengji.getlist(stuId);
        Gson gson = new Gson();
        String data = gson.toJson(empList);

        writer.println(data);
        writer.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
