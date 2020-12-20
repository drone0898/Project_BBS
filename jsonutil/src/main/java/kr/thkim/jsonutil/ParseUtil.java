package kr.thkim.jsonutil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ParseUtil {
    private static Gson gson = null;

    public static Gson getGson() {
        if(gson == null){
            gson = new GsonBuilder().create();
        }
        return gson;
    }

    public static <T> T fromJson(String json, Class<T> type){
        return getGson().fromJson(json,type);
    }

    public static <T> String toJson(T data){
        return getGson().toJson(data,data.getClass());
    }

    public static String readJsonFile(String filename) {
        String json = null;
        try {
            InputStream is = new FileInputStream(filename);
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

    public static <T> void writeToJsonFile(T data, String filename){
        try{
            FileWriter writer = new FileWriter(filename);
            getGson().toJson(data,writer);
            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
