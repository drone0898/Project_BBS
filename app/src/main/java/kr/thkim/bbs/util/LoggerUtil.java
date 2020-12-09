package kr.thkim.bbs.util;

import android.util.Log;

import com.orhanobut.logger.Logger;

import androidx.annotation.NonNull;
import kr.thkim.bbs.BuildConfig;

/**
 * 디버그 로그 Util
 * @author taeheunkim
 */
public final class LoggerUtil {

    public static void e(String msg) {
        if (BuildConfig.DEBUG) {
            if(Global.dontUseLogger){
                Log.e(Global.LOG_TAG,msg);
            }else{
                Logger.e(msg);
            }
        }
    }

    public static void e(@NonNull Exception e) {
        if (BuildConfig.DEBUG) {
            if (e.getCause() != null) {
                LoggerUtil.e(e.getCause().getMessage());
            }
            for (StackTraceElement element : e.getStackTrace()) {
                LoggerUtil.e(element.toString());
            }
        }
    }

    public static void e(@NonNull Throwable e) {
        if (BuildConfig.DEBUG) {
            LoggerUtil.e(e.getMessage());
            for (StackTraceElement element : e.getStackTrace()) {
                LoggerUtil.e(element.toString());
            }
        }
    }

    public static void d(String msg) {
        if (BuildConfig.DEBUG) {
            if(Global.dontUseLogger){
                Log.d(Global.LOG_TAG,msg);
            }else{
                Logger.d(msg);
            }
        } else {

        }
    }

    public static void saveLog(String date,String log){

    }
}
