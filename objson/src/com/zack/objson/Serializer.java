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
        return objectConvertJson(o);
    }

    /**
     * 将对象node转换成json串
     * @param node
     * @return
     */
    private String objectConvertJson(Object node) {
        if (node==null) {
            return "";
        }
        //对象是集合
        StringBuilder stringBuilder =  new StringBuilder();
        if (node instanceof Collection){
            Collection<?> collection = (Collection<?>) node;
            /*stringBuilder.append("[");*/
            stringBuilder.append(collectionConvertJson(collection));
            /*stringBuilder.append("]");*/
            return stringBuilder.toString();
        }
        //解析属性
        Field[] fields = node.getClass().getDeclaredFields();
        if (fields==null||fields.length==0){
            return stringBuilder.toString();
        }
        stringBuilder.append("{");
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
                continue;
            }

            String key = getFieldName(field);
            if (key==null) {
                //忽略属性
                continue;
            }
            stringBuilder.append("\"").append(key).append("\":");
            //属性是集合
            if (child instanceof Collection){
                Collection<?> collection = (Collection) child;
                stringBuilder.append(collectionConvertJson(collection)).append(",");
            }  else if (child instanceof CharSequence){
                stringBuilder.append("\"").append(child.toString()).append("\"").append(",");
                System.out.println("key:"+key+" value(CharSequence):"+child.toString());
            } else if (child instanceof Number||
                    child instanceof Boolean||
                    child instanceof Character){
                stringBuilder.append(child.toString()).append(",");
                System.out.println("key:"+key+" value(Number/Boolean):"+child.toString());
            } else if (child instanceof Number[]||
                    child instanceof Character[]||
                    child instanceof CharSequence[]){
                Object[] array = (Object[]) child;
                stringBuilder.append(arrayConvertJson(array)).append(",");
            } else{
                stringBuilder.append(objectConvertJson(child)).append(",");
            }
        }
        removeLastDot(stringBuilder);
        stringBuilder.append("}");
        return stringBuilder.toString();

    }

    /**
     * 将数组转换成json
     * @param child   必须是数组   Integer  Double Long Short Character Byte Boolean Float String
     * @return
     */
    private String arrayConvertJson(Object[] array) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        if (array instanceof String[]){

        } else if (array instanceof Number[]||
                array instanceof Character[]){

        } else if ()
        child instanceof Number[]||




        child instanceof Number[]||
                child instanceof Character[]||
                child instanceof CharSequence[]
        return new char[0];*/
        return stringBuilder.toString();
    }

    private String collectionConvertJson(Collection<?> collection) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        //列表类型数据
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            //属性是集合
            if (obj instanceof CharSequence){
                stringBuilder.append("\"").append(obj.toString()).append("\"").append(",");
            } else if (obj instanceof Number||
                    obj instanceof Boolean||
                    obj instanceof Character){
                stringBuilder.append(obj.toString()).append(",");
            } else{
                stringBuilder.append(objectConvertJson(obj)).append(",");
            }
            /*stringBuilder.append(objectConvertJson(obj));
            stringBuilder.append(",");*/
        }
        removeLastDot(stringBuilder);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private void removeLastDot(StringBuilder stringBuilder) {
        if (stringBuilder==null||stringBuilder.length()<=0) {
            return;
        }
        if (','==stringBuilder.charAt(stringBuilder.length()-1)){
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }
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
        StringBuilder stringBuilder = new StringBuilder();
        return field.getName();
    }
}
