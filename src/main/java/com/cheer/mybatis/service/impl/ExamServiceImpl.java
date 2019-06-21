package com.cheer.mybatis.service.impl;

import com.cheer.mybatis.dao.ExamMapper;
import com.cheer.mybatis.model.Exam;
import com.cheer.mybatis.service.ExamService;
import com.cheer.mybatis.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ExamServiceImpl implements ExamService {
    @Override
    public int insert(Exam exam) throws Exception {
        SqlSession session = MybatisUtils.getSession();
        int result = session.getMapper(ExamMapper.class).insert(exam);
        MybatisUtils.close(session);
        if (result != 1) {
            throw new Exception("插入失败");
        }
        return result;
    }

    @Override
    public List<Exam> getExam() {
        SqlSession session = MybatisUtils.getSession();
        List<Exam> examList = session.getMapper(ExamMapper.class).getExam();
        MybatisUtils.close(session);

        return examList;
    }

    @Override
    public List<Exam> getanswer(int id) {
        SqlSession session = MybatisUtils.getSession();
        List<Exam> list = session.getMapper(ExamMapper.class).getanswer(id);
        MybatisUtils.close(session);
        return list;
    }

    @Override
    public List<String> getkey() {
        SqlSession session = MybatisUtils.getSession();
        List<String> list = session.getMapper(ExamMapper.class).getkey();
        MybatisUtils.close(session);
        return list;
    }
}
