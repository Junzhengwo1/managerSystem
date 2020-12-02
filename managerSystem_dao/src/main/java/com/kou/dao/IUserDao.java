package com.kou.dao;

import com.kou.domain.Role;
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

    /**
     * 根据userId查询出当前用户没有关联的角色
     * @param userId
     * @return
     */
    @Select("select * from role where id not in (select roleId from users_role where userId=#{userId})")
    List<Role> findOtherRoles(int userId);


    /**
     * 给用户添加角色
     * @param userId
     * @param roleId
     */
    @Insert("insert into users_role(userId,roleId) value(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId")int userId,@Param("roleId")int roleId);
}
