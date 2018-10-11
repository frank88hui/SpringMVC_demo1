package com.changhui.service;

import com.changhui.dao.LogsMapper;
import com.changhui.pojo.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogsMapper logsMapper;

    @Override
    public void insert(Logs logs) {
        logsMapper.insertSelective(logs);
    }
}
