package cn.diyai.tg.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.diyai.tg.R;

/**
 * 统计，显示报表
 * Created by wangxiaomin on 2018/3/10.
 */

public class StatisticsFragment extends Fragment {
    public StatisticsFragment() {
        // Empty constructor required for fragment subclasses
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_statistics, container, false);
        return rootView;
    }
}

