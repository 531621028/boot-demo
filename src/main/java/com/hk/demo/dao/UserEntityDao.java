package com.hk.demo.dao;

import com.hk.demo.entity.UserEntity;

import java.util.List;

/**
 * Created by hukangkang 2018/8/14
 */
public interface UserEntityDao {

    UserEntity findById(long id);

    List<UserEntity> listAll();

    List<UserEntity> findAll(String name);

    UserEntity findByUserId(String username);
}
