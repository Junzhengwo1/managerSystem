package com.kou.dao;

import com.kou.domain.Role;
import org.apache.ibatis.annotations.Select;

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
    public List<Role> findRoleByUserId(int userId);


}
