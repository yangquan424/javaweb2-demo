package com.cheer.web.servlet;

import com.cheer.mybatis.model.Users;
import com.cheer.mybatis.service.UsersServce;
import com.cheer.mybatis.service.impl.UsersServceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AdminServlet", urlPatterns = "/servlet/AdminServlet")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        response.setCharacterEncoding("UTF-8");
        //获取用户名
        String username = request.getParameter("username");
        //获取密码
        String password = request.getParameter("psaaword");
        //格式化打印
        //System.out.println(String.format("username=%s,password=%s",username,password));

        Users users = new Users();
        UsersServce usersServce = new UsersServceImpl();

        users = usersServce.getlist(username);
        if (users != null) {

            if (username.equals(users.getUsername()) && password.equals(users.getPassword())) {
                //创建Session对象
                HttpSession session = request.getSession();
                //登录成功的信息写到Session中
                session.setAttribute("username", username);

                System.out.println("登陆成功");
                // 登录成功跳转到empList.html
                // (1)使用相对路径
                //response.sendRedirect("../empList.html");
                // (2)使用绝对路径跳转
                String contextPath = request.getContextPath();
                System.out.println("contextPath=" + contextPath);
                response.sendRedirect(contextPath + "/page_admin.html");
            } else {
                System.out.println("登录失败！");
                // 登录失败跳转到login.html继续登录
                response.sendRedirect("../admin.html");
            }
        }else {
            System.out.println("失败");
            response.sendRedirect("../admin.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
