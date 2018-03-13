package cn.diyai.tg.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangxiaomin on 2018/3/13.
 */

public class TimeUtil {
    public static String today(){
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("MMdd");
        return ft.format(dNow);
    }
}
