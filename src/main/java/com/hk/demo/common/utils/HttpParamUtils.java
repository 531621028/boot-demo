package com.hk.demo.common.utils;

import com.hk.demo.exception.ClientException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hukangkang 2018/8/14
 */
public class HttpParamUtils {

    public static String getParam(HttpServletRequest request, String name) {
        return request.getParameter(name);
    }

    public static long getParam(HttpServletRequest request, String name, long defaultValue) {
        String value = request.getParameter(name);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return NumberUtils.toLong(value, defaultValue);
    }

    public static String getRequiredParam(HttpServletRequest request, String paramName) {
        String value = request.getParameter(paramName);
        if (StringUtils.isBlank(value)) {
            throw new ClientException(paramName + "参数不能为空");
        }
        return value;
    }

    public static String getParam(HttpServletRequest request, String name, String defaultValue) {
        String value = request.getParameter(name);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return value;
    }

    public static boolean getParam(HttpServletRequest request, String name, boolean defaultValue) {
        String value = request.getParameter(name);
        if (value == null) {
            return defaultValue;
        } else {
            String ls = value.toLowerCase();
            return "true".equals(ls) || "1".equals(ls);
        }
    }

    public static int getParam(HttpServletRequest request, String name, int defaultValue) {
        String value = request.getParameter(name);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return NumberUtils.toInt(value, defaultValue);
    }
}
