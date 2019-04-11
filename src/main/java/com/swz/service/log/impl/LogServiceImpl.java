package com.swz.service.log.impl;

import com.swz.log.domain.Log;
import com.swz.mapper.LogMapper;
import com.swz.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Package: com.swz.service.personDO.impl
 * @Description: LogService
 * @author: swz
 * @date: 2018/7/23 10:35
 */
@Service
@Transactional(readOnly = true)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    @Transactional
    public void addLog(Log log) {
        logMapper.insert(log);
    }

    @Override
    @Transactional
    public void putLog(Log log) {
        logMapper.update(log);
    }

}
