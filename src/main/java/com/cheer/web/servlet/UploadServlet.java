package com.cheer.web.servlet;

import com.cheer.mybatis.export.Tosql;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.WriteListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "UploadServlet", urlPatterns = "/servlet/UploadServlet")
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 文件上传处理工厂
        FileItemFactory factory = new DiskFileItemFactory();
// 创建文件上传处理器
        ServletFileUpload upload = new ServletFileUpload(factory);
// 开始解析请求信息
        List items = null;
        String path = null;
        String uploadFileName = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        Iterator iter = items.iterator();
        while (iter.hasNext()) {
            FileItem item = (FileItem) iter.next();
            //item.isFormField()返回true说明是文本框参数，普通输入项的数据
            if (item.isFormField()){
                String fieldName = item.getFieldName();//
                String value = item.getString("UTF-8");//.getString("encType")乱码问题的解决
                request.setAttribute(fieldName, value);

            }else {
                //文件数据
                String fileName = item.getName();
                uploadFileName = fileName.substring(fileName.lastIndexOf("////") + 1);
                //UUID生成唯一的文件名
                //String uploadFileName = UUID.randomUUID()+ "." + suffix;
                //服务器下的某个位置
                path = "../data";
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdirs();
                }

                // 获取item中的上传文件的输入流
                InputStream in = item.getInputStream();
                // 创建一个文件输出流
                FileOutputStream out = new FileOutputStream(path+"/"+uploadFileName);
                // 创建一个缓冲区
                byte buffer[] = new byte[1024];
                // 判断输入流中的数据是否已经读完的标识
                int len = 0;
                // 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                while ((len = in.read(buffer)) > 0) {
                    // 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录
                    out.write(buffer, 0, len);
                }
                // 关闭输入流
                in.close();
                in=null;
                // 关闭输出流
                out.close();
                out=null;
                // 删除处理文件上传时生成的临时文件
                item.delete();
            }
        }
        //HttpSession session = request.getSession();
        String paths = "C:/Developer_Tools/apache-tomcat-9.0.20/data/"+uploadFileName;
        //session.setAttribute("path",paths);
        //response.sendRedirect("Page_adminServlet");

        //HttpSession session = request.getSession(true);
        //String path = (String) session.getAttribute("path");
        System.out.println(paths);
        Tosql tosql = new Tosql();
        tosql.into(paths);

        response.sendRedirect("../page_admin.html");
        //request.getRequestDispatcher("Page_adminServlet?fileName="+paths+uploadFileName+"").forward(request,response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
