package com.kou.dao;

import com.kou.domain.Role;
import org.apache.ibatis.annotations.*;

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

    /**
     * 查询所有角色信息
     * @return
     */
    @Select("select * from role")
    List<Role> findAll();

    /**
     * 新增角色
     * @param role
     */
    @Insert("insert into role(roleName,roleDesc) value(#{roleName},#{roleDesc})")
    void save(Role role);

    /**
     * 根据roleId查询角色详情
     * @param roleId
     * @return
     */
    @Select("select * from role where id=#{roleId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,
            many = @Many(select = "com.kou.dao.IPermissionDao.findPermissionByRoleId"))
    })
    Role findById(int roleId);

    /**
     * 根据roleId删除User_role表中角色
     * @param roleId
     */
    @Delete("delete from users_role where roleId=#{roleId}")
    void deleteFromUser_RoleByRoleId(int roleId);

    /**
     * 根据roleId删除role_permission表中的角色
     * @param roleId
     */
    @Delete("delete from role_permission where roleId=#{roleId}")
    void deleteFromRole_PermissionByRoleId(int roleId);

    /**
     * 根据id删除role表中数据
     * 注意 一点要在中间表和外键关联的表删除。
     *
     * @param roleId
     */
    @Delete("delete from role where id=#{roleId}")
    void deleteRoleById(int roleId);
}
