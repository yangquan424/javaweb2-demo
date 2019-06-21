package com.cheer.test;

import com.cheer.mybatis.model.Exam;
import com.cheer.mybatis.service.ExamService;
import com.cheer.mybatis.service.impl.ExamServiceImpl;
import com.cheer.mybatis.util.ExcelUtils;
import org.junit.Test;

public class test1 {
    @Test
    public void test01() {
        ExcelUtils excelUtils = new ExcelUtils();
        Exam exam = new Exam();
        ExamService examService = new ExamServiceImpl();
        try {
            String[] str = excelUtils.readExcel("D:\\tmp\\题目.xlsx");
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
