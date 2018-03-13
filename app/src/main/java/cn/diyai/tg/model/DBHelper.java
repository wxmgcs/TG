package cn.diyai.tg.model;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import cn.diyai.tg.presenter.FlagLibPresenter;
import cn.diyai.tg.presenter.SettingPresenter;

/**
 * Created by wangxiaomin on 2018/3/12.
 */

public class DBHelper extends SQLiteOpenHelper {


    Context mContext;
    private static DBHelper helper;

    public DBHelper(Context context){
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        mContext = context;
    }

    public static synchronized DBHelper getInstance(Context context){
        if (helper == null) {
            helper = new DBHelper(context);
        }
        return helper;
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(Constants.TAG, "on db create");

        String sql = "create table if not exists " +
                Constants.TABLE_NAME_FLAGLIB + " (Id integer primary key, name text, selected integer)";
        sqLiteDatabase.execSQL(sql);

        String tableName = Constants.TABLE_NAME_FLAGLIB;
        sqLiteDatabase.execSQL("insert into " + tableName + " (Id, name, selected) values (1, '上班', 1)");
        sqLiteDatabase.execSQL("insert into " + tableName + " (Id, name, selected)  values (2, '阅读', 1)");
        sqLiteDatabase.execSQL("insert into " + tableName + " (Id, name, selected)  values (3, '写代码', 0)");
        sqLiteDatabase.execSQL("insert into " + tableName + " (Id, name, selected)  values (4, '健身', 1)");
        sqLiteDatabase.execSQL("insert into " + tableName + " (Id, name, selected)  values (5, '交通', 1)");

        sql = "create table if not exists " +
                Constants.TABLE_NAME_SETTING + " (Id integer primary key, "+Constants.DB_SETTING_SLEEPTIME+" text, "+Constants.DB_SETTING_TIMEPARTICLE+" integer)";
        sqLiteDatabase.execSQL(sql);

        tableName =Constants.TABLE_NAME_SETTING;
        sqLiteDatabase.execSQL(String.format("insert into " + tableName + " (%s, %s) values ('10:30~6:30', 15)", Constants.DB_SETTING_SLEEPTIME,Constants.DB_SETTING_TIMEPARTICLE));


        sql = "create table if not exists " +
                Constants.TABLE_NAME_TIMELOGGER
                + " ("+Constants.DB_TIMELOGGER_ID+" integer primary key autoincrement, "
                +Constants.DB_TIMELOGGER_DATE+" text, "
                +Constants.DB_TIMELOGGER_FLAG+" integer, "
                +Constants.DB_TIMELOGGER_START+" text, "
                +Constants.DB_TIMELOGGER_END+" text)";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + Constants.TABLE_NAME_FLAGLIB;
        sqLiteDatabase.execSQL(sql);

        sql = "DROP TABLE IF EXISTS " + Constants.TABLE_NAME_SETTING;
        sqLiteDatabase.execSQL(sql);

        sql = "DROP TABLE IF EXISTS " + Constants.TABLE_NAME_TIMELOGGER;
        sqLiteDatabase.execSQL(sql);

        onCreate(sqLiteDatabase);
    }


    @Override
    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + Constants.TABLE_NAME_FLAGLIB;
        sqLiteDatabase.execSQL(sql);

        sql = "DROP TABLE IF EXISTS " + Constants.TABLE_NAME_SETTING;
        sqLiteDatabase.execSQL(sql);

        sql = "DROP TABLE IF EXISTS " + Constants.TABLE_NAME_TIMELOGGER;
        sqLiteDatabase.execSQL(sql);
    }

}
