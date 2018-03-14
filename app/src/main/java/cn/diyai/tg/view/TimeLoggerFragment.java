package cn.diyai.tg.view;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.diyai.tg.R;
import cn.diyai.tg.adapter.TimeLoggerAdapter;
import cn.diyai.tg.model.Constants;
import cn.diyai.tg.model.Setting;
import cn.diyai.tg.model.TimeLogger;
import cn.diyai.tg.presenter.SettingPresenter;
import cn.diyai.tg.presenter.TimeLoggerPresenter;


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
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Log.i(Constants.TAG,"click list item:"+pos);
            }
        });

        LayoutInflater inflater2 =getActivity().getLayoutInflater();
        TimeLoggerAdapter adapter = new TimeLoggerAdapter(getActivity(),inflater2);
        lv.setAdapter(adapter);
        getActivity().setTitle(R.string.time_logger);
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
    }
}
