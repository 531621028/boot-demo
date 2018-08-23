package com.hk.demo.common.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hukangkang 2018/8/15
 */
public class CustomSessionListener implements SessionListener {

    private static final Logger LOG = LoggerFactory.getLogger(CustomSessionListener.class);

    @Override
    public void onStart(Session session) {
        LOG.info("会话创建：" + session.getId());
    }

    @Override
    public void onStop(Session session) {
        LOG.info("会话停止：" + session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        LOG.info("会话过期：" + session.getId());
    }
}
