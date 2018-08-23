package com.hk.demo.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;

import com.hk.demo.dao.UserEntityDao;

import org.apache.shiro.authz.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by hukangkang 2018/8/16
 */
@Component
public class UserPermissionCache {

    @Autowired
    private UserEntityDao userEntityDao;

    private LoadingCache<String, List<Permission>> cache = CacheBuilder.newBuilder().expireAfterAccess(10, TimeUnit
            .MINUTES).initialCapacity(10).maximumSize(1000).build(new CacheLoader<String, List<Permission>>() {
        @Override
        public List<Permission> load(String key) {
            return loadPermission(key);
        }
    });

    private List<Permission> loadPermission(String userId) {
        userEntityDao.findByUserId(userId);
        return Lists.newArrayList();
    }

    public Collection<Permission> get(String userId) {
        return cache.getUnchecked(userId);
    }

    public void invalidate(String userId) {
        cache.invalidate(userId);
    }

}
