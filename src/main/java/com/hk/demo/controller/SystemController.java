package com.hk.demo.controller;

import com.hk.demo.common.data.JsonResult;
import com.hk.demo.common.utils.HttpParamUtils;
import com.hk.demo.common.utils.ResponseBuilder;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hukangkang 2018/8/15
 */
@RestController
@RequestMapping("sys")
public class SystemController {

    @RequestMapping("login")
    public JsonResult login(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(HttpParamUtils.getRequiredParam(request, "userId"),
                HttpParamUtils.getRequiredParam(request, "password"));
        try {
            subject.login(token);
            return ResponseBuilder.renderBoolean(true, "登录成功");
        } catch (Exception e) {
            return ResponseBuilder.renderBoolean(false, "用户名或密码错误");
        }
    }
}
