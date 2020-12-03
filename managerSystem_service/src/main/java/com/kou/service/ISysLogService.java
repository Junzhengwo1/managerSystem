package com.kou.service;

import com.kou.domain.SysLog;

/**
 * @author dell
 */
public interface ISysLogService {

    /**
     * 保存日志到数据库
     * @param sysLog
     */
    public void save(SysLog sysLog);
}
