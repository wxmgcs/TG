package cn.diyai.tg.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import cn.diyai.tg.model.Constants;
import cn.diyai.tg.model.DBHelper;
import cn.diyai.tg.model.FlagLib;
import cn.diyai.tg.model.Setting;

/**
 * Created by wangxiaomin on 2018/3/11.
 */

public class DBPresenter {

    DBPresenter dbPresenter;
    DBHelper dbHelper;

    public  DBPresenter(Context context){
        dbHelper =  new DBHelper(context);
    }

    public DBPresenter(){

    }


    /**
     * 初始化 标签库
     *
     */
    public  void initFlagLibData(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        String tableName = dbHelper.getTableNameFlagLib();

        db.execSQL("insert into " + tableName + " (Id, name, selected) values (1, '上班', 1)");
        db.execSQL("insert into " + tableName + " (Id, name, selected)  values (2, '阅读', 1)");
        db.execSQL("insert into " + tableName + " (Id, name, selected)  values (3, '写代码', 0)");
        db.execSQL("insert into " + tableName + " (Id, name, selected)  values (4, '健身', 1)");
        db.execSQL("insert into " + tableName + " (Id, name, selected)  values (5, '交通', 1)");

        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();

    }


    /**
     * 获取所有的标签
     * @return
     */
    public List<FlagLib> getFlagLibs(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from "+dbHelper.getTableNameFlagLib(),null);

        if (cursor.getCount() > 0) {
            List<FlagLib> FlagLibList = new ArrayList<FlagLib>(cursor.getCount());
            while (cursor.moveToNext()) {

                FlagLib flagLib = new FlagLib();
                flagLib.setName(cursor.getString(cursor
                        .getColumnIndex("name")));
                flagLib.setSelected(cursor.getInt(cursor
                        .getColumnIndex("selected")));
                flagLib.setId(cursor.getInt(cursor
                        .getColumnIndex("Id")));
                FlagLibList.add(flagLib);
            }
            db.close();
            return FlagLibList;
        }
        db.close();
        return null;
    }

    public List<FlagLib> getUsedFlagLibs(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from "+dbHelper.getTableNameFlagLib()+" where selected = 1",null);
        List<FlagLib> FlagLibList = null;
        if (cursor.getCount() > 0) {
            FlagLibList = new ArrayList<FlagLib>(cursor.getCount()+1);
            while (cursor.moveToNext()) {

                FlagLib flagLib = new FlagLib();
                flagLib.setName(cursor.getString(cursor
                        .getColumnIndex("name")));
                flagLib.setSelected(cursor.getInt(cursor
                        .getColumnIndex("selected")));
                flagLib.setId(cursor.getInt(cursor
                        .getColumnIndex("Id")));
                FlagLibList.add(flagLib);
            }

        }else{
            FlagLibList = new ArrayList<FlagLib>(1);
        }
        FlagLibList.add(new FlagLib(0,"请选择",0));
        db.close();
        return FlagLibList;
    }

    /**
     * 添加
     * @param flag
     * @return
     */
    public void addFlag(String flag){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        String tableName = dbHelper.getTableNameFlagLib();
        db.execSQL(String.format("insert into %s (name, selected) values ('%s', 0)",tableName,flag));
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    /**
     * 删除标签
     * @param id
     */
    public void delFlag(String id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        String tableName = dbHelper.getTableNameFlagLib();
        db.delete(tableName, "Id = ?", new String[]{id});
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    /**
     * 更新标签状态
     * @param id
     * @param status
     */
    public void updateFlagStatus(String id,boolean status){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        String tableName = dbHelper.getTableNameFlagLib();
        ContentValues cv = new ContentValues();
        if(status){
            cv.put("selected", 1);
        }else{
            cv.put("selected", 0);
        }

        db.update(tableName,
                cv,
                "Id = ?",
                new String[]{id});
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }


    /**
     * 初始化 设置
     *
     */
    public  void initSettingData(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        String tableName = dbHelper.getTableNameSetting();

        db.execSQL(String.format("insert into " + tableName + " (%s, %s) values ('10:30~6:30', 15)", Constants.DB_SETTING_SLEEPTIME,Constants.DB_SETTING_TIMEPARTICLE));

        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();

    }


    /**
     * 获取配置
     * @return
     */
    public Setting getSetting(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from "+dbHelper.getTableNameSetting()+" where id = 1",null);
        Setting setting = setting = new Setting();
        if (cursor.getCount() > 0) {
            List<FlagLib> FlagLibList = new ArrayList<FlagLib>(cursor.getCount());
            while (cursor.moveToNext()) {
                setting.setSleepTime(cursor.getString(cursor
                        .getColumnIndex(Constants.DB_SETTING_SLEEPTIME)));
                setting.setTimeParticle(cursor.getInt(cursor
                        .getColumnIndex(Constants.DB_SETTING_TIMEPARTICLE)));
            }
        }
        db.close();
        return setting;
    }

    /**
     * 更新配置
     * @return
     */
    public void updateSetting(Setting setting){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        String tableName = dbHelper.getTableNameSetting();
        ContentValues cv = new ContentValues();
        cv.put(Constants.DB_SETTING_SLEEPTIME, setting.getSleepTime());
        cv.put(Constants.DB_SETTING_TIMEPARTICLE, setting.getTimeParticle());

        db.update(tableName,
                cv,
                "Id = ?",
                new String[]{"1"});
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();

    }
}
