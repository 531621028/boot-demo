package com.hk.demo.shiro;

import com.hk.demo.common.data.JsonResult;
import com.hk.demo.common.data.ResultStatus;
import com.hk.demo.common.utils.JacksonUtils;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hukangkang 2018/8/15
 */
public class DemoFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    // 表示访问拒绝时是否自己处理，如果返回true表示自己不处理且继续拦截器链执行，返回false表示自己已经处理了
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                return executeLogin(request, response);
            } else {
                return true;
            }
        } else {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            if (req.getMethod().equals(RequestMethod.OPTIONS.name())) {
                resp.setStatus(HttpStatus.OK.value());
                return true;
            }
            //todo 可以在前段请求中加一个字段，后面根据这个字段进行判断是否为前端请求
            resp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
            resp.setHeader("Access-Control-Allow-Credentials", "true");
            resp.setContentType("application/json; charset=utf-8");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();
            JsonResult result = new JsonResult();
            result.setMessage("请先登录！");
            result.setSuccess(false);
            result.setCode(ResultStatus.NO_LOGIN);
            out.println(JacksonUtils.toJsonString(result));
            out.flush();
            out.close();
            return false;
        }
    }
}
