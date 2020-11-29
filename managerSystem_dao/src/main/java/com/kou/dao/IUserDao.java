package com.kou.dao;

import com.kou.domain.UserInfo;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @author dell
 */
public interface IUserDao {

    /**
     * 根据用户名查询用户； 该用户将作为登录的用户
     * @param username
     * @return
     */
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id",javaType = java.util.List.class,
            many = @Many(select = "com.kou.dao.IRoleDao.findRoleByUserId"))

    })
    public UserInfo findByUsername(String username);

}
