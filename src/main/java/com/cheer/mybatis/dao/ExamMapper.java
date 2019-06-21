package com.cheer.mybatis.dao;

import com.cheer.mybatis.model.Exam;

import java.util.List;

public interface ExamMapper {
    List<Exam> getExam();

    int insert(Exam exam);

    List<Exam> getanswer(int id);

    List<String> getkey();
}
