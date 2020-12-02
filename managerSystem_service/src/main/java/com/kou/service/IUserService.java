package com.kou.service;

import com.kou.domain.Role;
import com.kou.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author dell
 */
public interface IUserService extends UserDetailsService {


    List<UserInfo> findAll();

    void save(UserInfo userInfo);

    UserInfo findById(int id);

    List<Role> findOtherRoles(int userId);

    void addRoleToUser(int userId, int[] roleIds);
}
