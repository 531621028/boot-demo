package com.hk.demo.service;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by hukangkang 2018/8/16
 */
@Service
public class ShiroChainDefinitionsService {

    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    public synchronized void reloadFilterChains() {
        try {
            AbstractShiroFilter shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            PathMatchingFilterChainResolver resolver = (PathMatchingFilterChainResolver) shiroFilter
                    .getFilterChainResolver();
            // 过滤管理器
            DefaultFilterChainManager manager = (DefaultFilterChainManager) resolver.getFilterChainManager();
            // 清除权限配置
            manager.getFilterChains().clear();
            Map<String, String> filterChainDefinitionMap = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            // 重新设置权限
            shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitionMap(filterChainDefinitionMap));
            Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> chain : chains.entrySet()) {
                manager.createChain(chain.getKey(), chain.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> loadFilterChainDefinitionMap(Map<String, String> filterChainDefinitionMap) {
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/**", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "authc");
        return filterChainDefinitionMap;
    }
}
