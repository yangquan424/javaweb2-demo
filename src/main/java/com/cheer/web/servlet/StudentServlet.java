package com.cheer.web.servlet;

import com.cheer.mybatis.model.Chengji;
import com.cheer.mybatis.model.Users;
import com.cheer.mybatis.service.ChengjiService;
import com.cheer.mybatis.service.UsersServce;
import com.cheer.mybatis.service.impl.ChengjiServiceImpl;
import com.cheer.mybatis.service.impl.UsersServceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentServlet", urlPatterns = "/servlet/StudentServlet")
public class StudentServlet extends HttpServlet {
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


        UsersServce usersServce = new UsersServceImpl();

        Users users = usersServce.getlist(username);
        if (users != null) {

            if (username.equals(users.getUsername()) && password.equals(users.getPassword())) {
                //创建Session对象
                HttpSession session = request.getSession();
                //登录成功的信息写到Session中
                session.setAttribute("userId", users.getId());
                ChengjiService service = new ChengjiServiceImpl();
                List<Chengji> list = service.getlist(users.getId());
                if(list.size() == 0){
                    try {
                        service.insert(users.getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("登陆成功");
                // 登录成功跳转到empList.html
                // (1)使用相对路径
                //response.sendRedirect("../empList.html");
                // (2)使用绝对路径跳转
                String contextPath = request.getContextPath();
                System.out.println("contextPath=" + contextPath);
                response.sendRedirect(contextPath + "/student_test.html");
            } else {
                System.out.println("登录失败！");
                // 登录失败跳转到login.html继续登录
                response.sendRedirect("../student.html");
            }
        }else {
            System.out.println("失败");
            response.sendRedirect("../student.html");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
