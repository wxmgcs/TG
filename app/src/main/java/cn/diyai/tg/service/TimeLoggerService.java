package cn.diyai.tg.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.List;

import cn.diyai.tg.model.TimeLogger;
import cn.diyai.tg.presenter.TimeLoggerPresenter;

/**
 * Created by wangxiaomin on 2018/3/13.
 */

public class TimeLoggerService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public class MyBinder extends Binder {
        public TimeLoggerService getService() {
            return TimeLoggerService.this;
        }
    }
}
