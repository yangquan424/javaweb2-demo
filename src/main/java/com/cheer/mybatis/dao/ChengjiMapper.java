package com.cheer.mybatis.dao;

import com.cheer.mybatis.model.Chengji;

import java.util.List;

public interface ChengjiMapper {
    /**
     * 把考生id存入数据库
     * @param id
     * @return
     */
    int insert(int id);

    /**
     * 按照ID查询考生信息
     * @param id
     * @return
     */
    List<Chengji> getlist(int id);

    int update(Chengji id);
}
