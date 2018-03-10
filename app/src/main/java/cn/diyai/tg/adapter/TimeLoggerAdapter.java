package cn.diyai.tg.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import cn.diyai.tg.R;
import cn.diyai.tg.model.TimeLogger;

/**
 * Created by wangxiaomin on 2018/3/9.
 */

public class TimeLoggerAdapter extends BaseAdapter{

    private List<TimeLogger> mData;
    private LayoutInflater mInflater;

    public TimeLoggerAdapter(LayoutInflater inflater, List<TimeLogger> data){
        mInflater = inflater;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
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
        //获得ListView中的view
        View viewTimeLogger = mInflater.inflate(R.layout.item_timelogger,null);
        //获得学生对象
        TimeLogger timerLogger = mData.get(position);
        //获得自定义布局中每一个控件的对象。
        TextView area = (TextView) viewTimeLogger.findViewById(R.id.timelogger_area);
        TextView timeEdit = (TextView) viewTimeLogger.findViewById(R.id.timelogger_edit);
        area.setText(timerLogger.getStart()+"~"+timerLogger.getEnd());
        timeEdit.setText("上班");
        return viewTimeLogger ;
    }
}
