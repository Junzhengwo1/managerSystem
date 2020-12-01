package com.kou.dao;

import com.kou.domain.Role;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author dell
 */
public interface IRoleDao {

    /**
     * 根据用户id查询对应的角色
     * @param userId
     * @return
     */
    @Select("SELECT *FROM role WHERE id in (SELECT roleId FROM users_role WHERE userId=#{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,
            many = @Many(select = "com.kou.dao.IPermissionDao.findPermissionByRoleId"))
    })
    public List<Role> findRoleByUserId(int userId);


}
