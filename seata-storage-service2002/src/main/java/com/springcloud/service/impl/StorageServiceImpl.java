package com.springcloud.service.impl;

import org.springframework.stereotype.Service;
import com.springcloud.dao.StorageDao;
import com.springcloud.service.StorageService;

import javax.annotation.Resource;

@Service
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        storageDao.decrease(productId, count);
    }

}
