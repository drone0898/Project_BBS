package kr.thkim.bbs.database;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import kr.thkim.bbs.util.Global;

/**
 * Room Database 설정
 * usage : AppDatabase.getDatabase(context);
 * @author taeheunkim on 2020.10.22
// */
//@Database(entities = {}, version = 1, exportSchema = false)
//public abstract class AppDatabase extends RoomDatabase {
//
//    private static volatile AppDatabase INSTANCE;
//    private static final int NUMBER_OF_THREADS = 4;
//    public static final ExecutorService databaseWriterExecutor =
//            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
//
//    public static AppDatabase getDatabase(Context context) {
//        if (INSTANCE == null) {
//            synchronized (AppDatabase.class) {
//                if (INSTANCE == null) {
//                    // 인메모리 데이터베이스의 경우
////                    INSTANCE = Room.inMemoryDatabaseBuilder(context,AppDatabase.class)
////                            .allowMainThreadQueries()
////                            .build();
//                    INSTANCE = Room.databaseBuilder(context, AppDatabase.class, Global.APP_DATABASE)
//                            .allowMainThreadQueries()
//                            .build();
//                }
//            }
//        }
//
//        return INSTANCE;
//    }
//}
public class AppDatabase{

}