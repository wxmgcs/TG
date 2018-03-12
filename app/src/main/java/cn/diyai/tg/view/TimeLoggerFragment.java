package cn.diyai.tg.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.diyai.tg.R;
import cn.diyai.tg.adapter.TimeLoggerAdapter;
import cn.diyai.tg.model.Setting;
import cn.diyai.tg.model.TimeLogger;
import cn.diyai.tg.presenter.SettingPresenter;


/**
 * 我的页面
 * Created by wangxiaomin on 2018/3/9.
 */

public class TimeLoggerFragment extends Fragment {

    Setting setting;

    public TimeLoggerFragment() {
    }

    List<TimeLogger> mData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_timelogger, container, false);
        ListView lv =  rootView.findViewById(R.id.timeLogger);

        LayoutInflater inflater2 =getActivity().getLayoutInflater();

        setting = new SettingPresenter(getActivity()).getSetting();
//
        int flag = setting.getTimeParticle(); //每隔15分钟一个间隔
//        int flag = 15;
        //初始化数据
        initData(flag);
        //创建自定义Adapter的对象
        TimeLoggerAdapter adapter = new TimeLoggerAdapter(getActivity(),inflater2,mData);
        //将布局添加到ListView中
        lv.setAdapter(adapter);

        getActivity().setTitle(R.string.time_logger);
        return rootView;
    }

    /*
    初始化数据
     */
    private void initData(int flag) {
        if(flag <= 0){
            return ;
        }

        mData = new ArrayList<TimeLogger>();
        int count = 24*60/flag;
        TimeLogger timeLogger = null;
        String start = null;
        String end = null;

        for(int i =0; i <count;i++ ){
            start = String.format("%02d:%02d",i*flag/60,i*flag%60);
            end = String.format("%02d:%02d",(i+1)*flag/60,(i+1)*flag%60);
            timeLogger  = new TimeLogger(start,end);
            mData.add(timeLogger);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
    }
}
