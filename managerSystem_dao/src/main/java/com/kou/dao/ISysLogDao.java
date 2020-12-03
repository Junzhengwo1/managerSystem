package com.kou.dao;

import com.kou.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author dell
 */
public interface ISysLogDao {

    @Insert("insert into sysLog(visitTime,username,ip,url,executionTime,method) " +
            "value(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method}) ")
    public void save(SysLog sysLog);

    @Select("select * from sysLog")
    List<SysLog> findAll();
}
