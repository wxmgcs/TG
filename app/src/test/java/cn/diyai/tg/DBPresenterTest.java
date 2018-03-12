package cn.diyai.tg;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.ServiceTestCase;
import android.util.Log;

import org.junit.Test;

import java.lang.reflect.Method;

import cn.diyai.tg.model.Constants;

/**
 * Created by wangxiaomin on 2018/3/11.
 */

public class DBPresenterTest extends AndroidTestCase {
    private Context context;

    private Context getTestContext() {
        try {
            Method getTestContext = ServiceTestCase.class.getMethod("getTestContext");
            return (Context) getTestContext.invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test
    public void testGetUsedFlagLibs() throws Exception {
        Log.i(Constants.TAG,new DBPresenter(getTestContext()).getUsedFlagLibs().size()+"");
    }
}
