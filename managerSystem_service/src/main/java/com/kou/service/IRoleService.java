package com.kou.service;


import com.kou.domain.Permission;
import com.kou.domain.Role;

import java.util.List;

/**
 * @author dell
 */
public interface IRoleService {

    /**
     * 查询所有角色信息
     * @return
     */
    public List<Role> findAll();

    void save(Role role);

    Role findById(Integer roleId);

    void deleteRole(Integer roleId);

    List<Permission> findOtherPermissions(Integer roleId);

    void addPermissionToRole(Integer roleId, Integer[] permissionIds);
}
