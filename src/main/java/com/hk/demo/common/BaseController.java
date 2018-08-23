package com.hk.demo.common;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hukangkang 2018/8/15
 */
public class BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    protected void checkPermission(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        String url = request.getServletPath();
        LOG.info("请求的url为{}", url);
        //将请求改为Wildcard模式方便校验
        // todo 测试去掉权限验证
//        String permissionStr = url.substring(1).replaceAll("/", ":");
//        if (!subject.isPermitted(new WildcardPermission(permissionStr))) {
//            LOG.info("请求{}被拦截{}", url, System.currentTimeMillis());
//            throw new ClientException("当前用户权限不足");
//        }
    }
}
