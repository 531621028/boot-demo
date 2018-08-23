package com.hk.demo.common.utils;

import com.google.common.collect.Maps;

import com.hk.demo.common.data.JsonResult;

import java.util.Collection;
import java.util.Map;

/**
 * Created by hukangkang 2018/8/15
 */
public class ResponseBuilder {

    private Map<String, Object> map;

    private ResponseBuilder() {
        this.map = Maps.newHashMap();
    }

    public static ResponseBuilder create(Object obj) {
        ResponseBuilder builder = new ResponseBuilder();
        if (obj != null) {
            builder.map.putAll(ObjectUtils.toMapExclude(obj, new String[]{}));
        }
        return builder;
    }

    public static ResponseBuilder create(Collection collection) {
        ResponseBuilder builder = new ResponseBuilder();
        if (collection != null) {
            builder.map.put("itemList", collection);
        }
        return builder;
    }

    public ResponseBuilder put(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

    public JsonResult build() {
        JsonResult ret = new JsonResult();
        ret.setSuccess(true);
        ret.setData(map);
        return ret;
    }

    public static JsonResult renderBoolean(boolean success, String msg) {
        JsonResult ret = new JsonResult();
        ret.setMessage(msg);
        ret.setSuccess(success);
        return ret;
    }

}
