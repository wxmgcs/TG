package cn.diyai.tg.view;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import cn.diyai.tg.R;
import cn.diyai.tg.model.Constants;
import cn.diyai.tg.model.Setting;
import cn.diyai.tg.presenter.SettingPresenter;


/**
 * 我的页面
 * Created by wangxiaomin on 2018/3/9.
 */

public class SettingFragment extends Fragment implements View.OnClickListener {

    SettingPresenter settingPresenter;
    Setting setting;

    public SettingFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_setting, container, false);
        Button sleepTime = rootView.findViewById(R.id.setting_sleeptime);
        Button timeParticle = rootView.findViewById(R.id.setting_time_particle);

        sleepTime.setOnClickListener(this);
        timeParticle.setOnClickListener(this);
        getActivity().setTitle(R.string.setting);

        settingPresenter = new SettingPresenter(getActivity());
        setting = settingPresenter.getSetting();
        Log.i(Constants.TAG, setting.toString());
        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_sleeptime:
                break;
            case R.id.setting_time_particle:
                setTimeParticle();
                break;
        }

    }

    public void setTimeParticle() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.setting_alertdialog_timeparticle, null);
        Spinner spinner = (Spinner) view.findViewById(R.id.alertdialog_seting_timeparticle);
        final String[] timeParticles = getResources().getStringArray(R.array.timeParticle);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, timeParticles);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setting.setTimeParticle(Integer.parseInt(timeParticles[i]));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        builder.setView(view);
        builder.setMessage(R.string.choose_time_particle)
                .setPositiveButton(R.string.btn_sure, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        settingPresenter.updateSetting(setting);
                    }
                })
                .setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.create();
        builder.show();
    }
}
