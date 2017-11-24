package com.zack.objson;

public class SimpleJson<T> {
    ISerializer serializer;
    IParser<T> parser;

    public SimpleJson() {
        serializer = new Serializer();
        parser = new Parser();
    }

    public SimpleJson(ISerializer serializer) {
        this.serializer = serializer;
        parser = new Parser();
    }

    public SimpleJson(IParser<T> parser) {
        this.parser = parser;
        serializer = new Serializer();
    }

    public SimpleJson(ISerializer serializer, IParser<T> parser) {
        this.serializer = serializer;
        this.parser = parser;
    }

    public String toJson(Object jsonObj){
        return serializer.transform(jsonObj);
    }

    public T fromJson(String jsonString, Class<T> clazz){
        return parser.transform(jsonString);
    }
}
