package cn.diyai.tg.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.diyai.tg.model.Constants;
import cn.diyai.tg.model.DBHelper;
import cn.diyai.tg.model.FlagLib;
import cn.diyai.tg.model.Setting;

/**
 * Created by wangxiaomin on 2018/3/12.
 */

public class SettingPresenter {

    DBHelper dbHelper;
    SQLiteDatabase db;
    public  SettingPresenter(Context context){
        dbHelper = DBHelper.getInstance(context);
    }


    /**
     * 获取配置
     * @return
     */
    public Setting getSetting(){
        Setting setting = setting = new Setting();
        try{
            db =dbHelper.getReadableDatabase();
            String tableName =Constants.TABLE_NAME_SETTING;
            Cursor cursor = db.rawQuery("select * from "+tableName+" where id = 1",null);

            if (cursor.getCount() > 0) {
                List<Setting> FlagLibList = new ArrayList<Setting>(cursor.getCount());
                while (cursor.moveToNext()) {
                    setting.setSleepTime(cursor.getString(cursor
                            .getColumnIndex(Constants.DB_SETTING_SLEEPTIME)));
                    setting.setTimeParticle(cursor.getInt(cursor
                            .getColumnIndex(Constants.DB_SETTING_TIMEPARTICLE)));
                }
            }
            db.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return setting;
    }

    /**
     * 更新配置
     * @return
     */
    public void updateSetting(Setting setting){
        db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        String tableName =Constants.TABLE_NAME_SETTING;
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
