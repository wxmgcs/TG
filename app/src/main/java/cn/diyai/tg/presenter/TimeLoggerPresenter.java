package cn.diyai.tg.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.diyai.tg.R;
import cn.diyai.tg.model.Constants;
import cn.diyai.tg.model.DBHelper;
import cn.diyai.tg.model.Setting;
import cn.diyai.tg.model.TimeLogger;
import cn.diyai.tg.util.TimeUtil;

/**
 * Created by wangxiaomin on 2018/3/12.
 */

public class TimeLoggerPresenter {

    DBHelper dbHelper;
    SQLiteDatabase db;
    public TimeLoggerPresenter(Context context){
        dbHelper = DBHelper.getInstance(context);
    }

    public List<TimeLogger>  init(int flag){
        Log.i(Constants.TAG,"time logger init data");
        List<TimeLogger> timeLoggers = new ArrayList<>();
        String today = TimeUtil.today();
        int count = 24*60/flag;
        TimeLogger timeLogger = null;
        String start = null;
        String end = null;

        for(int i =0; i <count;i++ ){
            start = String.format("%02d:%02d",i*flag/60,i*flag%60);
            end = String.format("%02d:%02d",(i+1)*flag/60,(i+1)*flag%60);

            timeLogger  = new TimeLogger();
            timeLogger.setId(Integer.parseInt(today)*1000+i+1);
            timeLogger.setStart(start);
            timeLogger.setEnd(end);
            timeLogger.setDate(today);
            timeLogger.setFlag(0);
            timeLoggers.add(timeLogger);
            saveTimeLogger(timeLogger);
        }
        return timeLoggers;
    }

    /**
     * 获取配置
     * @return
     */
    public List<TimeLogger> getTimeLoggers(){


        List<TimeLogger> timeLoggers=null;
        db =dbHelper.getReadableDatabase();
        String tableName =Constants.TABLE_NAME_TIMELOGGER;
        String sql = "select * from "+tableName+" where "+Constants.DB_TIMELOGGER_DATE+" = "+TimeUtil.today();
//        Log.i(Constants.TAG,"getTimeLoggers:"+sql);
        Cursor cursor = db.rawQuery(sql,null);
//        Cursor cursor = db.rawQuery("select * from "+tableName +" order by Id",null);

        if (cursor.getCount() > 0) {
            timeLoggers = new ArrayList<TimeLogger>(cursor.getCount());
            while (cursor.moveToNext()) {
                TimeLogger timeLogger =  new TimeLogger();
                timeLogger.setDate(cursor.getString(cursor
                        .getColumnIndex(Constants.DB_TIMELOGGER_DATE)));
                timeLogger.setStart(cursor.getString(cursor
                        .getColumnIndex(Constants.DB_TIMELOGGER_START)));
                timeLogger.setEnd(cursor.getString(cursor
                        .getColumnIndex(Constants.DB_TIMELOGGER_END)));
                timeLogger.setId(cursor.getInt(cursor
                        .getColumnIndex(Constants.DB_TIMELOGGER_ID)));
                timeLogger.setFlag(cursor.getInt(cursor
                        .getColumnIndex(Constants.DB_TIMELOGGER_FLAG)));
//                Log.i(Constants.TAG,"get time logger:"+timeLogger.toString());
                timeLoggers.add(timeLogger);
            }
        }
        cursor.close();
        db.close();
        return timeLoggers;
    }

    /**
     * 获取配置
     * @return
     */
    public int getTimeLoggerCount(){
        try{
            db =dbHelper.getReadableDatabase();
            String tableName =Constants.TABLE_NAME_TIMELOGGER;
            Cursor cursor = db.rawQuery("select count(*) from "+tableName+" where "+Constants.DB_TIMELOGGER_DATE+" = "+TimeUtil.today(),
                    null);

            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    return cursor.getInt(cursor
                            .getColumnIndex("count(*)"));
                }
            }
            cursor.close();
            db.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * 更新配置
     * @return
     */
    public void updateTimeLogger(TimeLogger timelogger){
        Log.i(Constants.TAG,"update time logger:"+timelogger.toString());
        db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        String tableName =Constants.TABLE_NAME_TIMELOGGER;
        ContentValues cv = new ContentValues();
        cv.put(Constants.DB_TIMELOGGER_DATE, timelogger.getDate());
        cv.put(Constants.DB_TIMELOGGER_END, timelogger.getEnd());
        cv.put(Constants.DB_TIMELOGGER_START,timelogger.getStart());
        cv.put(Constants.DB_TIMELOGGER_ID,timelogger.getId());
        cv.put(Constants.DB_TIMELOGGER_FLAG,timelogger.getFlag());

        db.update(tableName,
                cv,
                "Id = ?",
                new String[]{timelogger.getId()+""});
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();

    }

    /**
     * 保存
     * @return
     */
    public void saveTimeLogger(TimeLogger timelogger){
        db = dbHelper.getWritableDatabase();
//        db.beginTransaction();
        String tableName =Constants.TABLE_NAME_TIMELOGGER;
//        ContentValues cv = new ContentValues();
//        cv.put(Constants.DB_TIMELOGGER_DATE, timelogger.getDate());
//        cv.put(Constants.DB_TIMELOGGER_END, timelogger.getEnd());
//        cv.put(Constants.DB_TIMELOGGER_START,timelogger.getStart());
//        cv.put(Constants.DB_TIMELOGGER_FLAG,timelogger.getFlag());
//        cv.put(Constants.DB_TIMELOGGER_ID,timelogger.getId());
//        Log.i(Constants.TAG,"save time logger:"+timelogger.toString());
//        db.insert(tableName,
//                null,
//                cv);
//        db.setTransactionSuccessful();
//        db.endTransaction();
        String sql = "insert into " + tableName + " ("+
                Constants.DB_TIMELOGGER_ID
                +","+Constants.DB_TIMELOGGER_DATE
                +","+Constants.DB_TIMELOGGER_FLAG
                +","+Constants.DB_TIMELOGGER_START
                +","+Constants.DB_TIMELOGGER_END
                +")  values ("
                +timelogger.getId()
                +","+timelogger.getDate()
                +","+"\""+timelogger.getFlag()+"\""
                +","+"\""+timelogger.getStart()+"\""
                +","+"\""+timelogger.getEnd()+"\""
                +")";
        Log.i(Constants.TAG,"save time logger:"+sql);
        db.execSQL(sql);
        db.close();
    }
}
