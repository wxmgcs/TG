package cn.diyai.tg.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
            timeLogger.setStart(start);
            timeLogger.setEnd(end);
            timeLogger.setDate(today);
            timeLogger.setFlag("请选择");
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
        TimeLogger timeLogger =  new TimeLogger();
        List<TimeLogger> timeLoggers=null;
        try{
            db =dbHelper.getReadableDatabase();
            String tableName =Constants.TABLE_NAME_TIMELOGGER;
            Cursor cursor = db.rawQuery("select * from "+tableName+" where "+Constants.DB_TIMELOGGER_DATE+" = 0313",null);

            if (cursor.getCount() > 0) {
                timeLoggers = new ArrayList<TimeLogger>(cursor.getCount());
                while (cursor.moveToNext()) {
                    timeLogger.setDate(cursor.getString(cursor
                            .getColumnIndex(Constants.DB_TIMELOGGER_DATE)));
                    timeLogger.setStart(cursor.getString(cursor
                            .getColumnIndex(Constants.DB_TIMELOGGER_START)));
                    timeLogger.setEnd(cursor.getString(cursor
                            .getColumnIndex(Constants.DB_TIMELOGGER_END)));
                    timeLogger.setId(cursor.getInt(cursor
                            .getColumnIndex(Constants.DB_TIMELOGGER_ID)));
                    timeLogger.setFlag(cursor.getString(cursor
                            .getColumnIndex(Constants.DB_TIMELOGGER_FLAG)));
                    timeLoggers.add(timeLogger);
                }
            }
            cursor.close();
            db.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return timeLoggers;
    }

    /**
     * 更新配置
     * @return
     */
    public void updateTimeLogger(TimeLogger timelogger){
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
        db.beginTransaction();
        String tableName =Constants.TABLE_NAME_TIMELOGGER;
        ContentValues cv = new ContentValues();
        cv.put(Constants.DB_TIMELOGGER_DATE, timelogger.getDate());
        cv.put(Constants.DB_TIMELOGGER_END, timelogger.getEnd());
        cv.put(Constants.DB_TIMELOGGER_START,timelogger.getStart());
        cv.put(Constants.DB_TIMELOGGER_FLAG,timelogger.getFlag());
        db.insert(tableName,
                Constants.DB_TIMELOGGER_DATE,
                cv);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();

    }
}
