package cn.diyai.tg.contract;

/**
 * Created by wangxiaomin on 2018/3/11.
 */

public interface IBaseFragment<T> {
    // 设置逻辑
    void setPresenter(T mIFragmentPresenter);
}
