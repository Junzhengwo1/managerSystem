package com.kou.service.impl;

import com.kou.dao.IUserDao;
import com.kou.domain.Role;
import com.kou.domain.UserInfo;
import com.kou.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JIAJUN KOU
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) {
        UserInfo userInfo=userDao.findByUsername(username);
        //如何将UserInfo 转换成 UserDetails
        //User user=new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));
        //用另一个构造；更加高级
        User user=new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),
                userInfo.getStatus()==0? false:true,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }

    //这个方法是返回一个list集合，集合中装的就是角色描述

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){

        List<SimpleGrantedAuthority> list=new ArrayList<SimpleGrantedAuthority>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }
}
