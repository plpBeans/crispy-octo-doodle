package vip.hengnai.wine.util;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 *
 * @author hua
 * @date 2019/8/22
 */

public class AppUtil {
    /**
     * 获取版本号名称
     *
     * @param context 上下文
     * @return
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }

}
