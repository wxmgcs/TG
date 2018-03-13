package cn.diyai.tg.util;

import android.test.AndroidTestCase;
import android.util.Log;

import cn.diyai.tg.model.Constants;

/**
 * Created by wangxiaomin on 2018/3/13.
 */

public class TimeUtilTest extends AndroidTestCase {
    public void testToday(){
        Log.d(Constants.TAG,TimeUtil.today());
    }
}
