package com.zhaopin.service;

import com.zhaopin.model.Imcc;

/**
 * Created by SYJ on 2017/4/16.
 */
public interface ImccService {
    Imcc selectImccById(Integer id);
    void insert(Imcc imcc);
}
