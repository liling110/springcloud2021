package com.springcloud.service.impl;

import com.springcloud.service.OrderHystrixService;
import org.springframework.stereotype.Service;

@Service
public class OrderHystrixFallBackServiceImpl implements OrderHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "=============OrderHystrixFallBackServiceImpl fall back-paymentInfo_OK,/(ćoć)/~~";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "=============OrderHystrixFallBackServiceImpl fall back-paymentInfo_TimeOut,/(ćoć)/~~";
    }
}
