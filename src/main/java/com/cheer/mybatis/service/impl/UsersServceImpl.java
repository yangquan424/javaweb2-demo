package com.cheer.mybatis.service.impl;

import com.cheer.mybatis.dao.UsersMapper;
import com.cheer.mybatis.model.Users;
import com.cheer.mybatis.service.UsersServce;
import com.cheer.mybatis.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UsersServceImpl implements UsersServce {
    @Override
    public int register(Users users) throws Exception {
        SqlSession session = MybatisUtils.getSession();
        int result = session.getMapper(UsersMapper.class).register(users);
        MybatisUtils.close(session);
        if (result != 1) {
            throw new Exception("插入失败");
        }
        return result;
    }

    @Override
    public Users getlist(String username) {
        SqlSession session = MybatisUtils.getSession();
        Users list = session.getMapper(UsersMapper.class).getlist(username);

        MybatisUtils.close(session);

        return list;
    }
}
