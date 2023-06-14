package com.learn.service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public String getOrders() {
        return "Get order";
    }
}
