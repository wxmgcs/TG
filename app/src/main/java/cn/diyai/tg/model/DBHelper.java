package cn.diyai.tg.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cn.diyai.tg.R;
import cn.diyai.tg.presenter.DBPresenter;

/**
 * Created by wangxiaomin on 2018/3/11.
 */

public class DBHelper extends SQLiteOpenHelper {
    private  static int DB_VERSION = 1;
    private  static String DB_NAME = "TG.db";
    private  String TABLE_NAME_FLAGLIB = "FlagLib";
    private  String TABLE_NAME_SETTING = "Setting";
    Context mContext;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mContext = context;

    }


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
        mContext = context;
        // TODO Auto-generated constructor stub
    }

    public String getTableNameFlagLib() {
        return TABLE_NAME_FLAGLIB;
    }

    public String getTableNameSetting() {
        return TABLE_NAME_SETTING;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // 创建标签库
        String sql = "create table if not exists " +
                TABLE_NAME_FLAGLIB + " (Id integer primary key, name text, selected integer)";
        sqLiteDatabase.execSQL(sql);

        //创建配置库
        sql = "create table if not exists " +
                TABLE_NAME_SETTING + " (Id integer primary key, sleepTime text, timeParticle integer)";
        sqLiteDatabase.execSQL(sql);

        isCreating = true;
        currentDB = sqLiteDatabase;

        DBPresenter dbPresenter = new DBPresenter(mContext);
        dbPresenter.initFlagLibData();
        dbPresenter.initSettingData();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME_FLAGLIB;
        sqLiteDatabase.execSQL(sql);

        sql = "DROP TABLE IF EXISTS " + TABLE_NAME_SETTING;
        sqLiteDatabase.execSQL(sql);


        onCreate(sqLiteDatabase);
    }


    boolean isCreating = false;
    SQLiteDatabase currentDB = null;

//    @Override
//    public SQLiteDatabase getWritableDatabase() {
//        // TODO Auto-generated method stub
//        if(isCreating && currentDB != null){
//            return currentDB;
//        }
//        return super.getWritableDatabase();
//    }

//    @Override
//    public SQLiteDatabase getReadableDatabase() {
//        // TODO Auto-generated method stub
//        if(isCreating && currentDB != null){
//            return currentDB;
//        }
//        return super.getReadableDatabase();
//    }

}
