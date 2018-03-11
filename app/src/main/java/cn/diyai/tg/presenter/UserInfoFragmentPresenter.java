package cn.diyai.tg.presenter;


import cn.diyai.tg.contract.UserInfoContract;

/**
 *
 */
public class UserInfoFragmentPresenter implements UserInfoContract.IUserInfoFragmentPresenter {
    // 用户信息Fragment接口
    private UserInfoContract.IUserInfoFragment mIUserInfoFragment;

    /**
     * 构造函数
     * @author leibing
     * @createTime 2016/08/23
     * @lastModify 2016/08/23
     * @param mIUserInfoFragment 用户信息Fragment接口
     * @return
     */
    public UserInfoFragmentPresenter(UserInfoContract.IUserInfoFragment mIUserInfoFragment) {
        this.mIUserInfoFragment = mIUserInfoFragment;
        // 设置逻辑
        mIUserInfoFragment.setPresenter(this);
    }

    @Override
    public void loadData() {
        System.out.println("ddddddddddddddddddd mIFragment loadData");
        mIUserInfoFragment.showData();
    }

    @Override
    public void start() {
        System.out.println("ddddddddddddddddddd mIFragment start");
        loadData();
    }
}
