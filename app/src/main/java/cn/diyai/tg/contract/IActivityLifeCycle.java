package cn.diyai.tg.contract;

/**
 * Created by wangxiaomin on 2018/3/11.
 */

public interface IActivityLifeCycle {
    void onCreate();
    void onRestart();
    void onStart();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();
}
