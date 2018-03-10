package cn.diyai.tg.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.diyai.tg.R;


/**
 * 我的页面
 * Created by wangxiaomin on 2018/3/9.
 */

public class FlagLibFragment extends Fragment {
    public FlagLibFragment() {
        // Empty constructor required for fragment subclasses
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_flaglib, container, false);
        return rootView;
    }
}
