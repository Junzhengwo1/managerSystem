package com.kou.dao;

import com.kou.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

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


    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from users")
    List<UserInfo> findAll();

    /**
     * 添加用户
     * @param userInfo
     */
    @Insert("insert into users(email,username,password,phoneNum,status)"
            + "value(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    /**
     * 通过id查询出用户信息
     * @param id
     * @return
     */
    @Select("select * from users where id=#{id}")
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
    UserInfo findById(int id);
}
