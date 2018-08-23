package com.hk.demo.shiro;

import com.hk.demo.cache.UserPermissionCache;
import com.hk.demo.dao.UserEntityDao;
import com.hk.demo.entity.UserEntity;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by hukangkang 2018/8/14
 */
@Component
public class DemoShiroRealm extends AuthorizingRealm {
    private static final Logger LOG = LoggerFactory.getLogger(DemoShiroRealm.class);


    @Autowired
    private UserEntityDao userEntityDao;
    @Autowired
    private UserPermissionCache userPermissionCache;

    /**
     * 提供用户信息返回权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        LOG.info("权限配置-->doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //todo 根据用户信息获取权限
        String userId = (String) principalCollection.getPrimaryPrincipal();
        authorizationInfo.addObjectPermissions(userPermissionCache.get(userId));
        return authorizationInfo;
    }

    /**
     * 提供账户信息返回认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        String userId = (String) authenticationToken.getPrincipal();
        LOG.info(authenticationToken.getCredentials().toString());
        UserEntity user = userEntityDao.findByUserId(userId);
        LOG.info("----->>userInfo=" + user);
        if (user == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(
                user.getUserId(),//用户信息
                user.getPassword(),// 用户验证信息
                ByteSource.Util.bytes(user.getNickName()),
                getName()  //realm name
        );
    }
}
