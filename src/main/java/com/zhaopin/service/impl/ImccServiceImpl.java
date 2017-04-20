package com.zhaopin.service.impl;

import com.zhaopin.dao.ImccDao;
import com.zhaopin.model.Imcc;
import com.zhaopin.service.ImccService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by SYJ on 2017/4/16.
 */
@Service
public class ImccServiceImpl implements ImccService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImccServiceImpl.class);

    @Autowired
    private ImccDao imccDao;

    @Override
    public Imcc selectImccById(Integer id) {
        return imccDao.selectImccById(id);
    }

    @Override
    public void insert(Imcc imcc) {
        imccDao.insert(imcc);
    }
}
