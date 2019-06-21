package com.cheer.mybatis.service;

import com.cheer.mybatis.model.Chengji;

import java.util.List;

public interface ChengjiService {
    /**
     * 把考生成绩存入数据库
     * @param
     * @return
     */
    int insert(int id) throws Exception;

    /**
     * 按照ID查询考生信息
     * @param id
     * @return
     */
    List<Chengji> getlist(int id);

    int update(Chengji chengji);
}
