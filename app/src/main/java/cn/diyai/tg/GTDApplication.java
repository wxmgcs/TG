package cn.diyai.tg;

import android.app.Application;
import android.content.Context;

import cn.diyai.tg.model.DBHelper;

/**
 * Created by wangxiaomin on 2018/3/12.
 */

public class GTDApplication extends Application {
    private Context context;
    private DBHelper dbHelper;

    public void onCreate() {
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
