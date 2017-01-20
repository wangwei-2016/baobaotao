package com.baobaotao.dao;

import com.baobaotao.domain.UserRoleKey;

import java.util.List;

public interface UserRoleMapper {
    int deleteByPrimaryKey(UserRoleKey key);

    int insert(UserRoleKey record);

    int insertSelective(UserRoleKey record);

}