package com.zack.objson;

import com.zack.objson.annotation.Alias;
import com.zack.objson.annotation.Ignore;
import com.zack.objson.annotation.SerializeName;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 序列化器：将Object转换成Json字符串
 */
class Serializer implements ISerializer {
    @Override
    public String transform(Object o) {
        if (o==null){
            throw new NullPointerException("obj is null");
        }
        return serialize(o);
    }

    private String serialize(Object node) {
        StringBuilder stringBuilder = new StringBuilder();
        if (node instanceof Collection){
            stringBuilder.append("[");
            //列表类型数据
            Collection<?> collection = (Collection<?>) node;
            Iterator<?> it = collection.iterator();
            while (it.hasNext()){
                Object obj = it.next();
                stringBuilder.append(serialize(obj));
                stringBuilder.append(",");
            }
            stringBuilder.append("]");
        } /*else if (node instanceof Map){
            //map类型数据

        } */else {
            stringBuilder.append("{");
            stringBuilder.append(convertObjectToJson(node));
            stringBuilder.append("}");
        }
        return stringBuilder.toString();
    }

    /**
     * 将对象转换成json
     * @param node
     * @return
     */
    private String convertObjectToJson(Object node) {
        StringBuilder stringBuilder =  new StringBuilder();
        if (node instanceof Collection){
            stringBuilder.append(serialize(node));
            return stringBuilder.toString();
        }
        Field[] fields = node.getClass().getDeclaredFields();
        if (fields==null||fields.length==0){
            return stringBuilder.toString();
        }
        for (Field field : fields){
            Object child = null;
            field.setAccessible(true);
            try {
                child = field.get(node);
                if (child==null){
                    //属性没有赋值
                    continue;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            String key = getFieldName(field);
            if (key==null) {
                //忽略属性
                continue;
            }

            if (child instanceof Collection){
                stringBuilder.append(serialize(child)).append(",");
            } else if (child instanceof CharSequence){
                stringBuilder.append(key).append(":\"").append(child.toString()).append("\"").append(",");
                System.out.println("key:"+key+" value(CharSequence):"+child.toString());
            } else if (child instanceof Number||
                    child instanceof Boolean||
                    child instanceof Character){
                stringBuilder.append(key).append(":").append(child.toString()).append(",");
                System.out.println("key:"+key+" value(Number/Boolean):"+child.toString());
            } else{
                stringBuilder.append(convertObjectToJson(child)).append(",");
            }
        }
        return stringBuilder.toString();

    }

    /**
     * 获取属性名
     * @param field
     * @return
     */
    private String getFieldName(Field field) {
        Ignore ignoreAnnotation = field.getAnnotation(Ignore.class);
        if (ignoreAnnotation!=null&&ignoreAnnotation.value()){
            return null;
        }

        SerializeName serializeNameAnnotation = field.getAnnotation(SerializeName.class);
        if (serializeNameAnnotation!=null){
            return serializeNameAnnotation.value();
        }
        Alias aliasAnnotation = field.getAnnotation(Alias.class);
        if (aliasAnnotation!=null){
            return aliasAnnotation.value();
        }
        return field.getName();
    }
}
