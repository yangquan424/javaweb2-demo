package com.cheer.mybatis.service;

import com.cheer.mybatis.model.Exam;

import java.util.List;

public interface ExamService {
    /**
     * 添加考试题目
     * @param exam
     * @return
     * @throws Exception
     */
    int insert(Exam exam) throws Exception;

    /**
     * 获取考试题目
     * @return
     */
    List<Exam> getExam();

    /**
     * 根据题目序号获取题目
     * @param id
     * @return
     */
    List<Exam> getanswer(int id);

    /**
     * 获取答案
     * @return
     */
    List<String> getkey();
}
