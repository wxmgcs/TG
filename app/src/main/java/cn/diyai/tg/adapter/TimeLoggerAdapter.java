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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.diyai.tg.R;
import cn.diyai.tg.TGApplication;
import cn.diyai.tg.model.Constants;
import cn.diyai.tg.model.FlagLib;
import cn.diyai.tg.model.Setting;
import cn.diyai.tg.model.TimeLogger;
import cn.diyai.tg.presenter.FlagLibPresenter;
import cn.diyai.tg.presenter.SettingPresenter;
import cn.diyai.tg.presenter.TimeLoggerPresenter;

/**
 * Created by wangxiaomin on 2018/3/9.
 */
public class TimeLoggerAdapter extends BaseAdapter {

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
    TimeLogger curTimerLogger;


    public TimeLoggerAdapter(Context context, LayoutInflater inflater) {
        mContext = context;
        mInflater = inflater;
        flagLibPresenter = new FlagLibPresenter(context);
        timeLoggerPresenter = new TimeLoggerPresenter(context);
        settingPresenter = new SettingPresenter(context);
        setting = settingPresenter.getSetting();
        flag = setting.getTimeParticle();
        timeLoggers = timeLoggerPresenter.getTimeLoggers();
        if (timeLoggers == null) {
            timeLoggers = timeLoggerPresenter.init(flag);
        }
        Log.i(Constants.TAG, "日志个数:" + timeLoggers.size() + "");
        flagLibs = flagLibPresenter.getUsedFlagLibs();
        Log.i(Constants.TAG, "使用标签数:" + flagLibs.size() + "");
        for (FlagLib flagLib : flagLibs) {
            Log.i(Constants.TAG, flagLib.toString());
        }

    }


    @Override
    public int getCount() {
        if (timeLoggers != null) {
            return timeLoggers.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return timeLoggers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;

        if(view == null){
            viewHolder = new ViewHolder();
            List<String> flagLibList = new ArrayList<String>();
            for (int i = 0; i < flagLibs.size(); i++) {
                flagLibList.add(flagLibs.get(i).getName());
            }


            view = mInflater.inflate(R.layout.item_timelogger, null);
            Log.i(Constants.TAG,"get view position:"+position+":"+timeLoggers.get(position).toString());
            curTimerLogger = timeLoggers.get(position);
            viewHolder.area = (TextView) view.findViewById(R.id.timelogger_area);
            viewHolder.spinner = (Spinner) view.findViewById(R.id.timelogger_edit);
            viewHolder.area.setText(curTimerLogger.getStart() + "~" + curTimerLogger.getEnd());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, flagLibList);
            viewHolder.spinner.setAdapter(adapter);
            viewHolder.spinner.setSelection(curTimerLogger.getFlag(), true);


            viewHolder.spinner.setOnItemSelectedListener(new ItemClickSelectListener(viewHolder.spinner));
//            view.setOnClickListener(this);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        return view;
    }

    private class ItemClickSelectListener implements AdapterView.OnItemSelectedListener {
        Spinner checkinfo_item_value ;

        public ItemClickSelectListener(Spinner checkinfo_item_value) {
            this.checkinfo_item_value = checkinfo_item_value;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {
//            allValues.put(checkinfo_item_value.getPrompt().toString(), position);
            //更新数据库
            Log.i(Constants.TAG, "current position:" + position + ":" + id + ":");
            curTimerLogger.setFlag(flagLibs.get(position).getId());
            timeLoggerPresenter.updateTimeLogger(curTimerLogger);
            Log.i(Constants.TAG,parent.getItemAtPosition(position)+"");
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {

        }
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();

    }


    private class ViewHolder {
        private Spinner spinner;
        private TextView area;
    }

}
