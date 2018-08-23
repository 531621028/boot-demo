/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hk.demo.common.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

public class ObjectUtils {

    private static final Logger LOG = LoggerFactory.getLogger(ObjectUtils.class);


    public static Map<String, Object> toMapExclude(Object obj, final String[] properties) {
        return toMap(obj, propertyName -> !ArrayUtils.contains(properties, propertyName));
    }


    private static Map<String, Object> toMap(Object obj, PropertyMatcher propertyMatcher) {
        Map<String, Object> map = new LinkedHashMap<>();
        PropertyDescriptor[] ps = BeanUtils.getPropertyDescriptors(obj.getClass());
        for (PropertyDescriptor p : ps) {
            String propertyName = p.getName();
            if (!"class".equals(propertyName) && propertyMatcher.match(propertyName)) {
                try {
                    Method readMethod = p.getReadMethod();
                    if (readMethod != null) {
                        Object value = readMethod.invoke(obj);
                        map.put(p.getName(), value);
                    }
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    LOG.error(null, ex);
                }
            }
        }
        return map;
    }

    public interface PropertyMatcher {
        boolean match(String propertyName);
    }
}
