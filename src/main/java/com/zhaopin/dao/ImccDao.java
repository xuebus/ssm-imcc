package com.zhaopin.dao;

import com.zhaopin.model.Imcc;

/**
 * Created by SYJ on 2017/4/16.
 */
public interface ImccDao {
    Imcc selectImccById(Integer id);
    void insert(Imcc imcc);
}
