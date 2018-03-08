package cn.diyai.tg.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.Locale;

import cn.diyai.tg.R;


/**
 * 我的页面
 * Created by wangxiaomin on 2018/3/9.
 */

public class TimeLoggerFragment extends Fragment {
    public TimeLoggerFragment() {
        // Empty constructor required for fragment subclasses
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_timelogger, container, false);
        ListView lv =  rootView.findViewById(R.id.timeLogger);

        getActivity().setTitle(R.string.time_logger);
        return rootView;
    }
}
