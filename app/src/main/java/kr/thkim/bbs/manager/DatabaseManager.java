package kr.thkim.bbs.manager;

import android.content.Context;

import kr.thkim.bbs.database.AppDatabase;
import kr.thkim.bbs.util.LoggerUtil;

public class DatabaseManager {

    private static AppDatabase appDatabase = null;

    public static AppDatabase get() {
        if(appDatabase == null){
            LoggerUtil.e("AppDatabase is Null, Need init");
        }
        return appDatabase;
    }

    public static void init(Context context){
//        appDatabase = AppDatabase.getDatabase(context);
    }
}
