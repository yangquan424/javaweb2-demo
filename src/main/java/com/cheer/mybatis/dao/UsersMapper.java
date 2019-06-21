package com.cheer.mybatis.dao;

import com.cheer.mybatis.model.Users;

public interface UsersMapper {

    int register(Users users);

    Users getlist(String username);

}
