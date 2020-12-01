package com.kou.dao;

import com.kou.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author dell
 */
public interface IPermissionDao {

    /**
     * 根据roleId查询Permission
     * @param roleId
     * @return
     */
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{roleId})")
    public List<Permission> findPermissionByRoleId(int roleId);


    /**
     * 查询所有permission
     * @return
     */
    @Select("select * from permission")
    List<Permission> findAll();

    /**
     * 新增资源
     * @param permission
     */
    @Insert("insert into permission(permissionName,url) value(#{permissionName},#{url})")
    void save(Permission permission);

    /**
     * 根据id查询出资源详情
     * @param id
     * @return
     */
    @Select("select * from permission where id=#{id}")
    Permission findById(int id);


    /**
     * 根据id删除role_permission
     * @param id
     */
    @Delete("delete from role_permission where permissionId=#{id}")
    void deleteFromRole_Permission(int id);

    /**
     * 更id删除本表中
     * @param id
     */
    @Delete("delete from permission where id=#{id}")
    void deleteById(int id);
}
