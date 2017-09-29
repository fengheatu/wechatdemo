package com.river.dao.mapper;

import com.river.model.pojo.TextMessage;

public interface TextMessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TextMessage record);

    int insertSelective(TextMessage record);

    TextMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TextMessage record);

    int updateByPrimaryKey(TextMessage record);
}