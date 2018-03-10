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

public class SettingFragment extends Fragment {
    public SettingFragment() {
        // Empty constructor required for fragment subclasses
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_setting, container, false);
        TextView sleepTime = rootView.findViewById(R.id.setting_sleeptime);
        TextView timeParticle = rootView.findViewById(R.id.setting_time_particle);

        sleepTime.setText(R.string.setting_sleeptime);
        timeParticle.setText(R.string.setting_time_particle);
        getActivity().setTitle(R.string.setting);
        return rootView;
    }
}
