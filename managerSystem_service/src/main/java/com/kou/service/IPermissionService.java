package com.kou.service;

import com.kou.domain.Permission;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author dell
 */

public interface IPermissionService {

    public List<Permission> findAll();


    void save(Permission permission);

    Permission findById(int id);



    void deletePermission(int id);
}
