package com.cheer.web.servlet;

import com.cheer.mybatis.model.Chengji;
import com.cheer.mybatis.service.ChengjiService;
import com.cheer.mybatis.service.ExamService;
import com.cheer.mybatis.service.impl.ChengjiServiceImpl;
import com.cheer.mybatis.service.impl.ExamServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "RewindServlet", urlPatterns = "/servlet/RewindServlet")
public class RewindServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(RewindServlet.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //获得考生的ID，相当于准考号
        int stuId = (Integer) session.getAttribute("userId");

        ExamService examService = new ExamServiceImpl();
        List<String> list = examService.getkey();

        //answerB:标准答案，answerB：考生答案,correct:答对，error:答错，miss:漏答，scores:分数，pass:是否合格
        String[] answerB = new String[list.size()];
        int j = 0;
        for (String s : list) {
            String s1 = s.substring(s.length()-1);
            answerB[j] = s1;
            j++;
        }
        String[] answerK = request.getParameterValues("answerK");
        LOGGER.debug(answerB);
        LOGGER.debug(answerK);
        int correct=0,error=0,miss=0,scores=0;
        String pass;
        for (int i = 0; i < answerK.length; i++) {
            if(answerK[i] == null||answerK[i] ==""){
                miss += 1;
            } else if(answerK[i].equals(answerB[i])){
                correct += 1;
                scores += 20;
            } else if((!answerK[i].equals(answerB[i]))&&(answerK[i] != null)){
                error += 1;
            }

        }
        miss += answerB.length - answerK.length;
        if(scores >= 100){
            pass = "合格";
        }else{
            pass = "不合格";
        }
        Chengji chengji = new Chengji(stuId,correct,error,miss,scores,pass);

        ChengjiService service = new ChengjiServiceImpl();
        PrintWriter writer = response.getWriter();
        try {
           int row = service.update(chengji);
           writer.println(row);
           writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
