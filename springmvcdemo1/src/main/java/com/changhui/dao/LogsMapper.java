package com.changhui.dao;

import com.changhui.pojo.Logs;
import com.changhui.pojo.LogsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogsMapper {
    long countByExample(LogsExample example);

    int deleteByExample(LogsExample example);

    int insert(Logs record);

    int insertSelective(Logs record);

    List<Logs> selectByExample(LogsExample example);

    int updateByExampleSelective(@Param("record") Logs record, @Param("example") LogsExample example);

    int updateByExample(@Param("record") Logs record, @Param("example") LogsExample example);
}