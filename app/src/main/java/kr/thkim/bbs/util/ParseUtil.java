package kr.thkim.bbs.util;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;

public final class ParseUtil {

    private static Gson gson = null;

    public static String loadJSONFromAsset(Context context, String filename) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }


    public static Gson getGson() {
        if(gson == null){
            gson = new GsonBuilder().create();
        }
        return gson;
    }

    public static <T> T fromJson(@NonNull String json, Class<T> type){
        return getGson().fromJson(json,type);
    }

    public static <T> String toJson(T data){
        return getGson().toJson(data,data.getClass());
    }

    /**
     * (HashMap<String,(JSON)Object>) mapData.get(key) => ArrayList<T>
     * @param mapData   hashmap
     * @param <T>   type
     * @return  ArrayList<T> or null
     */
    public static <T> ArrayList<T> mapObjectValueToList(@NonNull HashMap<String,Object> mapData,
                                                        String key, Class<T> clazz){
//        GsonBuilder builder = new GsonBuilder();
//        builder.registerTypeAdapter(Double.class, (JsonSerializer<Double>)
//                (src, typeOfSrc, context) -> {
//                    if(src == src.longValue()) {
//                        return new JsonPrimitive(String.valueOf(src.longValue()));
//                    }
//                    return new JsonPrimitive(src);
//                });
//        Gson gson = builder.create();
        Gson gson = getGson();

        ArrayList<T> list = null;
        if(mapData.containsKey(key)){
            Object listObject = mapData.get(key);
            list = gson.fromJson(gson.toJson(listObject), new ArrayListOfSomething<>(clazz));
        }
        return list;
    }


    /**
     * HashMap<String,Object> convert to HashMap<String,ArrayList<T>>
     * @param mapData   hashmap
     * @param <T>   type
     * @return  HashMap<String,ArrayList<T>>
     */
    public static <T> HashMap<String,ArrayList<T>> mapObjectValueToMapList(
            HashMap<String,Object> mapData, Class<T> clazz){
//        GsonBuilder builder = new GsonBuilder();
//        builder.registerTypeAdapter(Double.class, (JsonSerializer<Double>)
//                (src, typeOfSrc, context) -> {
//                    if(src == src.longValue()) {
//                        return new JsonPrimitive(String.valueOf(src.longValue()));
//                    }
//                    return new JsonPrimitive(src);
//                });
//        Gson gson = builder.create();
        Gson gson = getGson();

        HashMap<String,ArrayList<T>> result = new HashMap<>();
        for (String s : mapData.keySet()) {
            Object listObject = mapData.get(s);
            if (listObject != null) {
                ArrayList<T> list;
                list = gson.fromJson(gson.toJson(listObject), new ArrayListOfSomething<>(clazz));
                result.put(s,list);
            }
        }
        return result;
    }

    static class ArrayListOfSomething<X> implements ParameterizedType {

        private Class<?> wrapped;

        public ArrayListOfSomething(Class<X> wrapped) {
            this.wrapped = wrapped;
        }

        @NotNull
        public Type[] getActualTypeArguments() {
            return new Type[] {wrapped};
        }

        @NotNull
        public Type getRawType() {
            return ArrayList.class;
        }

        public Type getOwnerType() {
            return null;
        }
    }
}