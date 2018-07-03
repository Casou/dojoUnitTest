package com.bparent.dojo.unitTest.empty.util;

import com.google.gson.Gson;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;

public class JsonUtil {

    public static final Gson GSON = new Gson();

    @SneakyThrows
    public static String toJson(Object object) {
        return GSON.toJson(object);
    }

    @SneakyThrows
    public static <T> T toObject(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }

    @SneakyThrows
    public static <T> List<T> toObjectList(String json, Class<T[]> clazz) {
        return Arrays.asList(new Gson().fromJson(json, clazz));
    }

}
