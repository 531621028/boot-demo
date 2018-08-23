package com.hk.demo.aop;

import org.springframework.stereotype.Service;

/**
 * Created by hukangkang 2018/8/22
 */
@Service
public class BusinessService {
    public long say(long order, long id) {
        System.out.println("Business Code:" + order);
        return id;
    }
}
