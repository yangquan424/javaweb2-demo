package com.cheer.mybatis.export;

import com.cheer.mybatis.model.Exam;
import com.cheer.mybatis.service.ExamService;
import com.cheer.mybatis.service.impl.ExamServiceImpl;
import com.cheer.mybatis.util.ExcelUtils;

public class Tosql {
    public void into(String path){
        ExcelUtils excelUtils = new ExcelUtils();
        Exam exam = new Exam();
        ExamService examService = new ExamServiceImpl();
        try {
            String[] str = excelUtils.readExcel(path);
            for (int i = 0; i < str.length; i++) {
                if (str[i] == null || str[i] == "") {
                    continue;
                }
                int s = i % 6;
                switch (s) {
                    case 0:
                        exam.setQuestion(str[i]);
                        break;
                    case 1:
                        exam.setA(str[i]);
                        break;
                    case 2:
                        exam.setB(str[i]);
                        break;
                    case 3:
                        exam.setC(str[i]);
                        break;
                    case 4:
                        exam.setD(str[i]);
                        break;
                    case 5:
                        exam.setAnswer(str[i]);
                        examService.insert(exam);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
