package com.kou.service.impl;

import com.kou.dao.IPermissionDao;
import com.kou.domain.Permission;
import com.kou.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author JIAJUN KOU
 */
@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;
    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    @Override
    public Permission findById(int id) {
        return permissionDao.findById(id);
    }

    @Override
    public void deletePermission(int id) {
        //从role_permission表中删除
        permissionDao.deleteFromRole_Permission(id);
        //从role表中删除
        permissionDao.deleteById(id);
    }


}
