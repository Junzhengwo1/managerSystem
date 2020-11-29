package com.kou.dao;

import com.kou.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * @author dell
 */
public interface IMemberDao {

    /**
     * 根据id查询会员
     * @param id
     * @return
     */
    @Select("select * from member where id=#{id}")
    public Member findById(Integer id);
}
