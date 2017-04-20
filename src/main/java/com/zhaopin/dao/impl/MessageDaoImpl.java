package com.zhaopin.dao.impl;

import com.zhaopin.core.common.CommonDao;
import com.zhaopin.dao.MessageDao;
import com.zhaopin.model.Imcc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by SYJ on 2017/4/20.
 */
public class MessageDaoImpl extends CommonDao implements MessageDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageDaoImpl.class);

    String ns = "sql.mapper.ImccMapper.";

    @Override
    public void insert(Imcc imcc) {
        this.getSqlSession().insert(ns + "insert", imcc);
    }
}
