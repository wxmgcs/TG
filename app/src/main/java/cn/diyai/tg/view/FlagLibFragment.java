package cn.diyai.tg.view;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.diyai.tg.R;
import cn.diyai.tg.adapter.CheckboxAdapter;
import cn.diyai.tg.model.Constants;
import cn.diyai.tg.model.FlagLib;
import cn.diyai.tg.presenter.DBPresenter;


/**
 * 我的页面
 * Created by wangxiaomin on 2018/3/9.
 */

public class FlagLibFragment extends Fragment {

    private List<CheckBox> checkBoxs = new ArrayList<CheckBox>();
    CheckboxAdapter listItemAdapter;

    DBPresenter dbPresenter = null;
    public FlagLibFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dbPresenter = new DBPresenter(getActivity());
        View rootView = inflater.inflate(R.layout.fragment_flaglib, container, false);

        LinearLayout linearLayout  = (LinearLayout)rootView.findViewById(R.id.flaglib_linerlayout);
        ListView listView = (ListView)rootView.findViewById(R.id.flaglib_listview);
        listItemAdapter = new CheckboxAdapter(getActivity(), initData());
        listView.setAdapter(listItemAdapter);

        setHasOptionsMenu(true);


        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.flag_lib, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_addflag:
                addFlagLib();
                return true;
            default:
        return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 选择复选框后，弹出保存按钮
     */
    public void delFlagLib(){
        HashMap<Integer, Boolean> state =listItemAdapter.getState();
        ArrayList<Integer> removeItems = new ArrayList<Integer>();

        for(int j=0;j<listItemAdapter.getCount();j++){
            if(state.get(j)!=null){
                removeItems.add(j);
            }
        }
        listItemAdapter.removeItem(removeItems);

    }

    public void addFlagLib(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.flaglib_alertdialog_add, null);
        builder.setView(view);
        builder.setMessage(R.string.add_flaglib)
                .setPositiveButton(R.string.btn_sure, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText flaglibName = (EditText)view.findViewById(R.id.alertdialog_flaglib_name);
                        if(view.equals("")){
                            Toast.makeText(getActivity(),R.string.enter_flaglib_name,Toast.LENGTH_SHORT).show();
                        }
                        if(!listItemAdapter.addItem(getActivity(),flaglibName.getText().toString())){
                            Toast.makeText(getActivity(),R.string.flaglib_is_exists,Toast.LENGTH_SHORT).show();
                        };
                    }
                })
                .setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.create();
        builder.show();

    }

    public ArrayList<HashMap<String, Object>> initData(){

        List<FlagLib> flagLibs = dbPresenter.getFlagLibs();

        ArrayList<HashMap<String, Object>> listData=new ArrayList<HashMap<String,Object>>();
        for(int i=0;i<flagLibs.size();i++){
            HashMap<String, Object> map=new HashMap<String, Object>();
            map.put("flaglib_name", flagLibs.get(i).getName());
            map.put("selected", flagLibs.get(i).getSelected() == 1 ? true:false);
            map.put("id", flagLibs.get(i).getId());
            listData.add(map);
        }
        return listData;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }
}
