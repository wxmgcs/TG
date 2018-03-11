package cn.diyai.tg.contract;

import cn.diyai.tg.model.UserInfoModel;

/**
 * Created by wangxiaomin on 2018/3/11.
 */

public class UserInfoContract {

    public interface IUserInfoActivity extends IBaseActivity<IUserInfoActivityPresenter> {
        void showLoading();//展示加载框
        void dismissLoading();//取消加载框展示
        void showUserInfo(UserInfoModel userInfoModel);//将网络请求得到的用户信息回调
        String loadUserId();//假设接口请求需要一个userId
    }

    public interface IUserInfoFragment extends IBaseFragment<IUserInfoFragmentPresenter> {
        void showData(); // 假定显示数据
    }


    public interface IUserInfoActivityPresenter extends IBasePresenter {
        void loadUserInfo();
    }

    public interface IUserInfoFragmentPresenter extends IBasePresenter {
        void loadData();
    }

}
