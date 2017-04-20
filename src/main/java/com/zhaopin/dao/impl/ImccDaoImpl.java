package com.zhaopin.dao.impl;

import com.zhaopin.core.common.CommonDao;
import com.zhaopin.dao.ImccDao;
import com.zhaopin.model.Imcc;
import org.springframework.stereotype.Repository;

/**
 * Created by SYJ on 2017/4/16.
 */
@Repository
public class ImccDaoImpl extends CommonDao implements ImccDao {

    String ns = "sql.mapper.ImccMapper.";

    @Override
    public Imcc selectImccById(Integer id) {
        return this.getSqlSession().selectOne(ns + "selectTbImccByIdWithRM", id);
    }

    @Override
    public void insert(Imcc imcc) {
        this.getSqlSession().insert(ns + "insert", imcc);
    }
}
