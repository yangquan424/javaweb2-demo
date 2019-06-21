package com.cheer.web.servlet;



import com.cheer.mybatis.export.Tosql;
import com.cheer.mybatis.model.Exam;
import com.cheer.mybatis.service.ExamService;
import com.cheer.mybatis.service.impl.ExamServiceImpl;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "Page_adminServlet", urlPatterns = "/servlet/Page_adminServlet")
public class Page_adminServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Page_adminServlet.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //String path = request.getParameter("fileName");

        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        String id = request.getParameter("num");
        ExamService empService = new ExamServiceImpl();
        //List<Exam> empList = empService.getanswer(Integer.valueOf(id));
        List<Exam> empList = empService.getExam();
        Gson gson = new Gson();
        String data = gson.toJson(empList);

        writer.println(data);
        writer.close();
        //response.sendRedirect("../page_admin.html");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
