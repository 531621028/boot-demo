package com.hk.demo.handle;

import com.hk.demo.common.data.JsonResult;
import com.hk.demo.common.data.ResultStatus;
import com.hk.demo.common.utils.JacksonUtils;
import com.hk.demo.exception.ClientException;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by hukangkang 2018/8/15
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandle {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ClientException.class)
    public String handleClientException(ClientException e) {
        JsonResult result = new JsonResult();
        result.setMessage(e.getMessage());
        result.setSuccess(false);
        result.setCode(e.getErrorCode());
        return JacksonUtils.toJsonString(result);
    }
}

