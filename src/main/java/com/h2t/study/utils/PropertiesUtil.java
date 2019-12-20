package com.h2t.study.utils;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * 对象拷贝工具类
 *
 * @author hetiantian
 * @version 1.0
 * @date: 2019/12/13 15:22
 */
public class PropertiesUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtil.class);

    public static void copyProperties(Object dest, Object orig) {
        try {
            PropertyUtils.copyProperties(dest, orig);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOGGER.error("properties copy throw exception, dest:{}, orig:{}, e:{}", dest, orig, e);
        }

    }
}
