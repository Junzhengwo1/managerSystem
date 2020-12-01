package com.kou.service;


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

    Role findById(int roleId);

    void deleteRole(int roleId);
}
