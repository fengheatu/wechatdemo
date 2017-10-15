package com.river.util;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Map;

/**
 * create by river  2017/10/10
 * desc:
 */
public class BeanUtil<T> {


    /**
     * map to 0bject
     * @param map
     * @param obj
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T mapToObject(Map<String, Object> map, T obj) throws Exception {

        if (null == map && map.isEmpty()) {
            return null;
        }
        BeanUtils.populate(obj, map);
        return obj;
    }

    /**
     * object to map
     * @param obj
     * @return
     */
    public static Map<?,?> objectToMap(Object obj) {
        if(null == obj) {
            return null;
        }
        return new BeanMap(obj);
    }



}
