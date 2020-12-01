package com.kou.dao;

import com.kou.domain.Permission;
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


}
