package cn.diyai.tg.contract;

/**
 * Created by wangxiaomin on 2018/3/11.
 */

public interface IBaseActivity<T>  {
    // 设置逻辑
    void setPresenter(T mIActivityPresenter);
    // 设置生命周期
    void setILifeCycle(IActivityLifeCycle mIActivityLifeCycle);
}
