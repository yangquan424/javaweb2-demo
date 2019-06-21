package com.cheer.mybatis.service.impl;

import com.cheer.mybatis.dao.ChengjiMapper;
import com.cheer.mybatis.model.Chengji;
import com.cheer.mybatis.service.ChengjiService;
import com.cheer.mybatis.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ChengjiServiceImpl implements ChengjiService {
    @Override
    public int insert(int id) throws Exception {
        SqlSession session = MybatisUtils.getSession();
        int result = session.getMapper(ChengjiMapper.class).insert(id);
        MybatisUtils.close(session);
        if (result != 1) {
            throw new Exception("插入失败");
        }
        return result;
    }

    @Override
    public List<Chengji> getlist(int id) {
        SqlSession session = MybatisUtils.getSession();
        List<Chengji> chengjiList = session.getMapper(ChengjiMapper.class).getlist(id);
        MybatisUtils.close(session);
        return chengjiList;
    }

    @Override
    public int update(Chengji id) {
        SqlSession session = MybatisUtils.getSession();
        int row = session.getMapper(ChengjiMapper.class).update(id);
        MybatisUtils.close(session);
        return row;
    }

}
