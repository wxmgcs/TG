package cn.diyai.tg.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import cn.diyai.tg.R;


/**
 * 我的页面
 * Created by wangxiaomin on 2018/3/9.
 */

public class MyFragment extends Fragment {
    public MyFragment() {
        // Empty constructor required for fragment subclasses
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mini, container, false);
        TextView textView = rootView.findViewById(R.id.mini);

        textView.setText("我的页面");
        getActivity().setTitle("我的页面");
        return rootView;
    }
}
