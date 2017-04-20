package com.zhaopin.service.impl;

import com.zhaopin.dao.ImccDao;
import com.zhaopin.model.Imcc;
import com.zhaopin.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by SYJ on 2017/4/20.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private ImccDao imccDao;

    @Override
    public void insert(Imcc imcc) {
        imccDao.insert(imcc);
    }
}
