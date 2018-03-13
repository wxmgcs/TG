package cn.diyai.tg;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.List;

import cn.diyai.tg.model.Constants;
import cn.diyai.tg.model.DBHelper;
import cn.diyai.tg.model.TimeLogger;
import cn.diyai.tg.presenter.TimeLoggerPresenter;

/**
 * Created by wangxiaomin on 2018/3/12.
 */

public class TGApplication extends Application {
    private Context context;
    private DBHelper dbHelper;


    public void onCreate() {
        Log.i(Constants.TAG,"TG is Starting...");
        super.onCreate();
        context = getApplicationContext();
        dbHelper = DBHelper.getInstance(context);
        new DBHelper(context);
    }

    public void onTerminate() {
        super.onTerminate();
        dbHelper.close();
    }
}
