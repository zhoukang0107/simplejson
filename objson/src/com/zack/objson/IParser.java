package com.zack.objson;

/**
 * 解析器，将Json字符串解析为object
 */
public interface IParser<T> {
    T transform(String jsonString);
}
