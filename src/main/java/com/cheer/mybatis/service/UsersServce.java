package com.cheer.mybatis.service;

import com.cheer.mybatis.model.Users;

public interface UsersServce {

    int register(Users users) throws Exception;

    Users getlist(String username);

}
