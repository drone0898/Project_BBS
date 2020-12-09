package kr.thkim.bbs.manager;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import kr.thkim.bbs.BaseApplication;
import kr.thkim.bbs.util.LoggerUtil;
import kr.thkim.bbs.util.ParseUtil;

/**
 * Use EncryptedSharedPreference.
 * Local Data (SharedPreference 및 Encrypted Data) 저장/읽기
 * 데이터를 JSON 형태로 저장하며, 함수 사용의 편의를 위해 모델의 객체를 파라미터로 받는다.
 *
 * 평문은 LOCAL_DATA_PLAIN 에 저장된다.
 * @author taeheunkim on 2020.06.11
 */
public class LocalDataManager {
    public static final String LOCAL_DATA_PLAIN = "LOCAL_DATA_PLAIN";

    private static LocalDataManager instance;

    private SharedPreferences plainPreferences;

    public static LocalDataManager getInstance() {
        if (instance == null) {
            if(BaseApplication.getBaseApplication()!=null){
                instance = new LocalDataManager();
            }
        }
        return instance;
    }

    public static void init() {
        instance = new LocalDataManager();
    }

    public LocalDataManager() {
        try {
            plainPreferences = BaseApplication.getBaseApplication()
                    .getSharedPreferences(LOCAL_DATA_PLAIN, Context.MODE_PRIVATE);
        } catch (Exception e) {
            LoggerUtil.e(e);
        }
    }


    /**
     * SharedPreference에 저장된 특정 객체를 읽어온다.
     * 예를들어 SharedPreference에 new Person("김태흔","940910") 이 JSON으로 저장돼있고 이를 읽어오고 싶다면
     * Person myPerson = getData(Person.class); 와 같이 사용한다.
     *
     * @param type       Class
     * @param <T>        Custom Class
     * @return class Instance from SharedPreference, failed : return null
     */
    public <T> T getData(Class<T> type) {
        T data = null;
        SharedPreferences preferences = plainPreferences;
        if(preferences==null){
            LoggerUtil.e("getData Failed!, Null Preference Exception");
            return null;
        }
        try {
            String value = preferences.getString(type.getSimpleName(), "");
            if (!value.equals("")) {
                data = ParseUtil.fromJson(value, type);
            }
        } catch (Exception e) {
            LoggerUtil.e("getData FAILED!! : " + type + "\n" + e);
        }
        return data;
    }

    /**
     * SharedPreference에 특정 객체를 저장한다.
     * 예를들어 Person person = new Person("김태흔","940910") 을 저장하고 싶다면
     * boolean success = saveData(person); 와 같이 사용한다.
     *
     * @param data       저장 데이터
     * @param <T>        data type
     * @return commit success
     */
    @SuppressLint("CommitPrefEdits")
    public <T> boolean saveData(T data) {
        SharedPreferences.Editor editor;
        if(plainPreferences==null){
            LoggerUtil.e("Save Data Failed!, Null Preference Exception");
            return false;
        }
        editor = plainPreferences.edit();
        String toJson = ParseUtil.toJson(data);
        boolean result = editor.putString(data.getClass().getSimpleName(), toJson).commit();
        if (!result) {
            LoggerUtil.e("Save Data Failed!");
        }
        return result;
    }


    /**
     * 저장된 데이터 삭제
     *
     * @param data       .
     * @param <T>        .
     * @return 성공여부
     */
    public <T> boolean deleteData(Class<T> data) {
        SharedPreferences preferences;
        if(plainPreferences==null){
            LoggerUtil.e("Save Data Failed!, Null Preference Exception");
            return false;
        }
        preferences = plainPreferences;
        if(!preferences.contains(data.getSimpleName())){
            return true;
        }
        SharedPreferences.Editor editor = preferences.edit();
        boolean result = editor.remove(data.getSimpleName()).commit();
        if (!result) {
            LoggerUtil.e("Remove Data Failed!");
        }
        return result;
    }

    public <T> boolean deleteData(boolean encryption, T data) {
        return deleteData(encryption, data.getClass());
    }
}
