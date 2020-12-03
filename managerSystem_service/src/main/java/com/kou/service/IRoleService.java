package com.kou.service;


import com.kou.domain.Permission;
import com.kou.domain.Role;

import java.util.List;

/**
 * @author dell
 */
public interface IRoleService {


    public List<Role> findAll(Integer page,Integer size);

    void save(Role role);

    Role findById(Integer roleId);

    void deleteRole(Integer roleId);

    List<Permission> findOtherPermissions(Integer roleId);

    void addPermissionToRole(Integer roleId, Integer[] permissionIds);
}
