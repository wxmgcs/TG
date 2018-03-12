package cn.diyai.tg.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.diyai.tg.model.Constants;
import cn.diyai.tg.model.DBHelper;
import cn.diyai.tg.model.FlagLib;

/**
 * Created by wangxiaomin on 2018/3/12.
 */

public class FlagLibPresenter {

    SQLiteDatabase db;
    DBHelper dbHelper;
    Context mContext;

    public FlagLibPresenter(Context context) {
        dbHelper = DBHelper.getInstance(context);
        mContext = context;
    }


    /**
     * 获取所有的标签
     *
     * @return
     */
    public List<FlagLib> getFlagLibs() {
        db = dbHelper.getReadableDatabase();
        String tableName = Constants.TABLE_NAME_FLAGLIB;

        Cursor cursor = db.rawQuery("select * from " + tableName, null);

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
            cursor.close();
            db.close();
            return FlagLibList;
        }
        cursor.close();
        db.close();
        return null;
    }

    public List<FlagLib> getUsedFlagLibs() {

        db = dbHelper.getReadableDatabase();
        String tableName = Constants.TABLE_NAME_FLAGLIB;

        Cursor cursor = db.rawQuery("select * from " + tableName + " where selected = 1", null);
        List<FlagLib> FlagLibList = null;
        if (cursor.getCount() > 0) {
            FlagLibList = new ArrayList<FlagLib>(cursor.getCount() + 1);
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

        } else {
            FlagLibList = new ArrayList<FlagLib>(1);
        }
        FlagLibList.add(new FlagLib(0, "请选择", 0));
        cursor.close();
        db.close();
        return FlagLibList;
    }

    /**
     * 添加
     *
     * @param flag
     * @return
     */
    public void addFlag(String flag) {
        db = dbHelper.getWritableDatabase();
        String tableName = Constants.TABLE_NAME_FLAGLIB;
        db.beginTransaction();
        db.execSQL(String.format("insert into %s (name, selected) values ('%s', 0)", tableName, flag));
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    /**
     * 删除标签
     *
     * @param id
     */
    public void delFlag(String id) {
        db = dbHelper.getWritableDatabase();
        String tableName = Constants.TABLE_NAME_FLAGLIB;
        db.beginTransaction();
        db.delete(tableName, "Id = ?", new String[]{id});
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    /**
     * 更新标签状态
     *
     * @param id
     * @param status
     */
    public void updateFlagStatus(String id, boolean status) {
        db = dbHelper.getWritableDatabase();
        String tableName = Constants.TABLE_NAME_FLAGLIB;
        db.beginTransaction();
        ContentValues cv = new ContentValues();
        if (status) {
            cv.put("selected", 1);
        } else {
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


}
