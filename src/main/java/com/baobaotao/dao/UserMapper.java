package com.baobaotao.dao;

import com.baobaotao.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    User getByUserName(String userName);

    int getMatchCount(@Param("userName") String userName, @Param("pwd") String pwd);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}