package cn.diyai.tg.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.diyai.tg.R;
import cn.diyai.tg.model.Constants;
import cn.diyai.tg.model.FlagLib;
import cn.diyai.tg.model.Setting;
import cn.diyai.tg.model.TimeLogger;
import cn.diyai.tg.presenter.FlagLibPresenter;
import cn.diyai.tg.presenter.SettingPresenter;
import cn.diyai.tg.presenter.TimeLoggerPresenter;

/**
 *
 * Created by wangxiaomin on 2018/3/9.
 */
public class TimeLoggerAdapter extends BaseAdapter{

    private List<TimeLogger> mData;
    private LayoutInflater mInflater;
    private Context mContext;
    private List<FlagLib> flagLibs = null;
    FlagLibPresenter flagLibPresenter;
    TimeLoggerPresenter timeLoggerPresenter;
    SettingPresenter settingPresenter;
    Setting setting;
    int flag;
    List<TimeLogger> timeLoggers;

    public TimeLoggerAdapter(Context context, LayoutInflater inflater){
        mContext = context;
        mInflater = inflater;
        flagLibPresenter = new FlagLibPresenter(context);
        timeLoggerPresenter = new TimeLoggerPresenter(context);
        settingPresenter = new SettingPresenter(context);
        setting = settingPresenter.getSetting();
        flag = setting.getTimeParticle(); //每隔15分钟一个间隔
        timeLoggers = timeLoggerPresenter.init(flag);
        Log.i(Constants.TAG,"日志个数:"+timeLoggers.size()+"");
        flagLibs = flagLibPresenter.getUsedFlagLibs();
        Log.i(Constants.TAG,"使用标签数:"+flagLibs.size()+"");
        for(FlagLib flagLib:flagLibs){
            Log.i(Constants.TAG,flagLib.toString());
        }
    }

    @Override
    public int getCount() {
        if(timeLoggers !=null){
            return timeLoggers.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        List<String> flagLibList=new ArrayList<String>();
        for(int i = 0 ; i < flagLibs.size();i++){
            flagLibList.add(flagLibs.get(i).getName());
        }

        //获得ListView中的view
        View viewTimeLogger = mInflater.inflate(R.layout.item_timelogger,null);

        //获得学生对象
//        TimeLogger timerLogger = mData.get(position);
        final TimeLogger timerLogger = timeLoggers.get(position);
        //获得自定义布局中每一个控件的对象。
        TextView area = (TextView) viewTimeLogger.findViewById(R.id.timelogger_area);
        Spinner timeEdit = (Spinner) viewTimeLogger.findViewById(R.id.timelogger_edit);
        area.setText(timerLogger.getStart()+"~"+timerLogger.getEnd());

        timeEdit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                //更新数据库
                timerLogger.setFlag(flagLibs.get(pos).getName());
                timeLoggerPresenter.updateTimeLogger(timerLogger);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<String> adapter =new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,flagLibList);
        timeEdit.setAdapter(adapter);
        timeEdit.setSelection(flagLibs.size()-1,true);


        return viewTimeLogger ;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();

    }
}
