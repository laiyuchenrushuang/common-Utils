package com.seatrend.utilsdk.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by seatrend on 2018/8/21.
 */

public class GsonUtils {

    private static Gson gson=new Gson();

    public static <T> T gson(String json, Class<T> clas) throws JsonSyntaxException{
        return gson.fromJson(json, clas);
    }

    public static String toJson(Object o){
        String s = gson.toJson(o);
        return s;
    }
    public static <T> ArrayList<T> jsonToArrayList(String json, Class<T> clazz) {
        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);

        ArrayList<T> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            arrayList.add(new Gson().fromJson(jsonObject, clazz));
        }
        return arrayList;
    }
}
