package cn.diyai.tg.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import cn.diyai.tg.R;
import cn.diyai.tg.model.Constants;
import cn.diyai.tg.presenter.FlagLibPresenter;

/**
 * Created by wangxiaomin on 2018/3/11.
 */
public class CheckboxAdapter extends BaseAdapter {
    Context context;
    ArrayList<HashMap<String, Object>> listData;
    //记录checkbox的状态
    HashMap<Integer, Boolean> state = new HashMap<Integer, Boolean>();
    FlagLibPresenter flagLibPresenter;

    //构造函数
    public CheckboxAdapter(Context context,ArrayList<HashMap<String,Object>> listData) {
        this.context = context;
        this.listData = listData;
        flagLibPresenter = new FlagLibPresenter(context);
    }

    @Override
    public int getCount() {
        if(listData != null){
            return listData.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
// TODO Auto-generated method stub
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
// TODO Auto-generated method stub
        return position;
    }

    /** 添加item数据 */
    public boolean addItem(Context context,String text) {
        if (listData != null){
            // 重复验证
            for(int i = 0; i < listData.size();i++){
                if(listData.get(i).get("flaglib_name").equals(text)){
                    return false;
                }
            }

            HashMap<String, Object> map=new HashMap<String, Object>();
            map.put("flaglib_name", text);
            map.put("selected", false);
            listData.add(map);// 添加数据
            flagLibPresenter.addFlag(text);
        }
        notifyDataSetChanged();
        return true;
    }

    public void removeItem(ArrayList<Integer> items){
        for(int i = 0 ; i < items.size();i++){
            Log.i(Constants.TAG,"删除标签:"+items.get(i));
            Log.i(Constants.TAG,listData.remove(items.get(i))+"");
        }

        notifyDataSetChanged();

        for(int i = 0;i< listData.size();i++){
            Log.i(Constants.TAG,"listData.item."+listData.get(i));
        }
    }

    // 重写View
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
// TODO Auto-generated method stub

        LayoutInflater mInflater = LayoutInflater.from(context);
        convertView = mInflater.inflate(R.layout.item_flaglib, null);
        TextView flaglibName = (TextView) convertView.findViewById(R.id.flaglib_name);
        flaglibName.setText((String) listData.get(position).get("flaglib_name"));
        CheckBox check = (CheckBox) convertView.findViewById(R.id.flaglib_checkbox);
        if((boolean)listData.get(position).get("selected")){
            check.setChecked(true);
        }else{
            check.setChecked(false);
        }

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
// TODO Auto-generated method stub
                if (isChecked) {
                    state.put(position, isChecked);
                } else {
                    state.remove(position);
                }

                flagLibPresenter.updateFlagStatus(listData.get(position).get("id")+"",isChecked);
            }
        });
//        check.setChecked((state.get(position) == null ? false : true));
        return convertView;
    }

    public HashMap<Integer, Boolean> getState(){
        return state;
    }
}
