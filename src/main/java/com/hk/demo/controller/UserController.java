package com.hk.demo.controller;

import com.hk.demo.common.BaseController;
import com.hk.demo.common.data.JsonResult;
import com.hk.demo.common.utils.HttpParamUtils;
import com.hk.demo.common.utils.ResponseBuilder;
import com.hk.demo.dto.User;
import com.hk.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hukangkang 2018/8/14
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "detail", method = {RequestMethod.GET})
    public JsonResult detail(HttpServletRequest request) {
        checkPermission(request);
        User user = userService.findById(HttpParamUtils.getParam(request, "id", 0L));
        return ResponseBuilder.create(user).build();
    }

    @RequestMapping("list")
    public JsonResult list(HttpServletRequest request) {
        checkPermission(request);
        List<User> userList = userService.listAll();
        return ResponseBuilder.create(userList).build();
    }

    @RequestMapping("find")
    public JsonResult find(HttpServletRequest request) {
        checkPermission(request);
        String nickName = HttpParamUtils.getParam(request, "nickName", "");
        List<User> userList = userService.findAll(nickName);
        return ResponseBuilder.create(userList).build();
    }
}
