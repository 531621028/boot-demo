package com.hk.demo.service;

import com.hk.demo.dao.UserEntityDao;
import com.hk.demo.dto.User;
import com.hk.demo.entity.UserEntity;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hukangkang 2018/8/14
 */
@Service
public class UserService {
    @Autowired
    private UserEntityDao userEntityDao;

    public User findById(long id) {
        UserEntity userEntity = userEntityDao.findById(id);
        if (userEntity == null) {
            userEntity = new UserEntity();
        }
        User user = new User();
        BeanUtils.copyProperties(userEntity, user);
        return user;
    }

    public List<User> listAll(){
        List<UserEntity> userEntityList = userEntityDao.listAll();
        return userEntityList.stream().map( u->{User user = new User();
        BeanUtils.copyProperties(u, user);
        return user;}).collect(Collectors.toList());
    }

    public List<User> findAll(String nickName){
        List<UserEntity> userEntityList = userEntityDao.findAll(nickName);
        return userEntityList.stream().map( u->{User user = new User();
            BeanUtils.copyProperties(u, user);
            return user;}).collect(Collectors.toList());
    }


    public User findByUserId(String userId) {
        UserEntity userEntity = userEntityDao.findByUserId(userId);
        User user = new User();
        BeanUtils.copyProperties(userEntity, user);
        return user;
    }
}
