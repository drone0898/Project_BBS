package kr.thkim.bbs;

import android.content.res.Configuration;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogcatLogStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.multidex.MultiDexApplication;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.plugins.RxJavaPlugins;
import kr.thkim.bbs.manager.DatabaseManager;
import kr.thkim.bbs.manager.LocalDataManager;
import kr.thkim.bbs.util.Global;
import kr.thkim.bbs.util.LoggerUtil;

public class BaseApplication extends MultiDexApplication {

    private static BaseApplication baseApplication;
    public static void setBaseApplication(BaseApplication _baseApplication){
        baseApplication = _baseApplication;
    }
    public static BaseApplication getBaseApplication(){
        return baseApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setBaseApplication(this);
        setLoggerInfo();
//        Global.cantUseLogger = true;
        hookRxJavaError();
        LocalDataManager.init();
        DatabaseManager.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    // solve io.reactivex.exceptions.UndeliverableException
    // @see https://softwaree.tistory.com/35
    // https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0#error-handling
    private void hookRxJavaError() {
        RxJavaPlugins.setErrorHandler(e -> {
            if (e instanceof UndeliverableException) {
                e = e.getCause();
            }
            if (e instanceof IOException) {
                LoggerUtil.e("fine, irrelevant network problem or API that throws on cancellation");
                return;
            }
            if (e instanceof InterruptedException) {
                LoggerUtil.e("fine, some blocking code was interrupted by a dispose call");
                return;
            }
            if ((e instanceof NullPointerException) || (e instanceof IllegalArgumentException)) {
                LoggerUtil.e("that's likely a bug in the application");
                Objects.requireNonNull(Thread.currentThread().getUncaughtExceptionHandler())
                        .uncaughtException(Thread.currentThread(), e);
                return;
            }
            if (e instanceof IllegalStateException) {
                LoggerUtil.e("that's a bug in RxJava or in a custom operator");
                Objects.requireNonNull(Thread.currentThread().getUncaughtExceptionHandler())
                        .uncaughtException(Thread.currentThread(), e);
                return;
            }
            LoggerUtil.d("Undeliverable exception received, not sure what to do\n" + e);
        });
    }


    private void setLoggerInfo() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)
                .methodCount(2)
                .methodOffset(5)
                .logStrategy(new LogcatLogStrategy())
                .tag(Global.LOG_TAG)
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    /**
     * @param filePrefix 파일이름 ("char001.png" => "char")
     * @param digit 자리수 ("001.png" => 3)
     * @param id id ("001.png" => 1)
     * @return resourceId, 없으면 기본이미지
     */
    public static int getDrawableFileResId(String filePrefix,int digit,int id){
        int result = baseApplication.getResources().getIdentifier(filePrefix + String.format(
                Locale.KOREAN, "%0"+digit+"d",id),"drawable",getBaseApplication()
                .getPackageName());
        return result == 0 ? R.drawable.bser : result;
    }
}
