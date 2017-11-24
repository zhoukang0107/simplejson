package com.zack.objson;

/**
 * 序列化器：将Object转换成Json字符串
 */
public interface ISerializer<T> {
    String transform(T t);
}
