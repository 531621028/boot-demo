package com.hk.demo.controller;

import com.hk.demo.common.BaseController;
import com.hk.demo.common.data.JsonResult;
import com.hk.demo.common.utils.ResponseBuilder;
import com.hk.demo.service.ShiroChainDefinitionsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hukangkang 2018/8/15
 */
@RestController
@RequestMapping(path = "admin", name = "管理员")
public class AdminController extends BaseController {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    ShiroChainDefinitionsService filterChainDefinitionsService;

    @RequestMapping(path = "url", name = "列出url")
    public JsonResult list(HttpServletRequest request) {
        checkPermission(request);
        List<Map<String, String>> urlList = new ArrayList<>();
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            Map<String, String> hashMap = new HashMap<>();
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            PatternsRequestCondition p = info.getPatternsCondition();
            for (String url : p.getPatterns()) {
                hashMap.put("url", url);
            }
            hashMap.put("className", method.getMethod().getDeclaringClass().getName()); // 类名
            hashMap.put("method", method.getMethod().getName()); // 方法名
            hashMap.put("name", info.getName());
            RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
            String type = methodsCondition.toString();
            if (type.startsWith("[") && type.endsWith("]")) {
                type = type.substring(1, type.length() - 1);
                hashMap.put("type", type); // 方法名
            }
            urlList.add(hashMap);
        }
        return ResponseBuilder.create(urlList).build();
    }

    /**
     * 加载权限设置，新的执行添加，相同的覆盖
     */
    @RequestMapping(path = "reloadPermission", name = "重新加载权限")
    public JsonResult reloadPermission(HttpServletRequest request) {
        checkPermission(request);
        filterChainDefinitionsService.reloadFilterChains();
        return ResponseBuilder.renderBoolean(true, "执行成功");
    }

}
