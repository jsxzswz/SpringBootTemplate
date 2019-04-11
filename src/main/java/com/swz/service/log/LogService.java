package com.swz.service.log;

import com.swz.log.domain.Log;

/**
 * @Package: com.swz.service
 * @Description: LogService
 * @author: swz
 * @date: 2018/7/23 10:09
 */
public interface LogService {

    void addLog(Log log);

    void putLog(Log log);

}
