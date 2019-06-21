package com.cheer.mybatis.service;

import com.cheer.mybatis.model.Exam;

import java.util.List;

public interface ExamService {
    int insert(Exam exam) throws Exception;

    List<Exam> getExam();

    List<Exam> getanswer(int id);

    /**
     * 获取答案
     * @return
     */
    List<String> getkey();
}
