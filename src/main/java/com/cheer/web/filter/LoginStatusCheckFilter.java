package com.cheer.web.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginStatusCheckFilter", urlPatterns = "", initParams = @WebInitParam(name = "ignoreURI",
        value = "login.html;servlet/LoginServlet;servlet/DeleteEmpServlet"))
public class LoginStatusCheckFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(LoginStatusCheckFilter.class);

    private String[] ignoreURI;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        // /javaweb-demo/login.html
        String uri = request.getRequestURI();
        // http://localhost:8080/javaweb-demo/login.html
        String url = request.getRequestURL().toString();
        LOGGER.debug("uri={}", uri);
        LOGGER.trace("url={}", url);

        // 判断是否为忽视放行的uri
        if (!ignoreRequest(uri)) {
            HttpSession session = request.getSession();
            // 从session里取出登录信息
            Object username = session.getAttribute("username");
            // 如果session里不存在该信息，则进行跳转到登录页面
            if (null == username) {
                response.sendRedirect(request.getContextPath() + "/login.html");
                return;
            }
        }

        // 放行
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        String value = config.getInitParameter("ignoreURI");
        this.ignoreURI = value.split(";");
    }

    // 判断当前uri是否以忽视uri为结尾
    public boolean ignoreRequest(String uri) {
        for (String s : ignoreURI) {
            if (uri.endsWith(s)) {
                return true;
            }
        }
        return false;
    }
}