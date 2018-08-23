package com.hk.demo.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JacksonJsonParser;

import java.io.IOException;

import jdk.nashorn.internal.ir.ObjectNode;

/**
 * Created by hukangkang 2018/8/14
 */
public class JacksonUtils {
    private static final Logger LOG = LoggerFactory.getLogger(ObjectUtils.class);

    public static ObjectMapper mapper = new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

    public static String toJsonString(Object t) {
        try {
            return mapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            LOG.error("对象转换成JSON失败", e);
            return "";
        }
    }

    public static <T> T praseObject(String jsonStr, Class<T> clazz) {
        try {
            return mapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            return null;
        }
    }
}
