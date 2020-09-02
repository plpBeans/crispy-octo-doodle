package vip.hengnai.wine;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 * @author hua
 * @date 2019/8/21
 */
public class Constants {

    public static String getBaseUrl() {
        return BuildConfig.BASE_URL;
    }
    public static String getVersionName(){return  BuildConfig.VERSION_NAME;}
    /**
     * 获取数组长度
     */
    public static final String GET_LIST_PAGE_SIZE_20 = "20";
    /**
     * 获取数组默认下标
     */
    public static final String GET_LIST_PAGE_INDEX = "0";


    /** 验证码 action**/
    public static  final  String ACTION_VERTIFCODE = "android.intent.action.yanzhenma";

    /** 验证码 action**/
    public static  final  String ACTION_VERTIFCODE2 = "android.intent.action.refresh";
    /**
     * 常量判断
     */
    public static final String STATUS="0";

    /**
     * 常量判断
     */
    public static final String YI_ZHU="yizhu";

    /**
     * 常量判断
     */
    public static final String ZHAO_HU="zhaohu";
    /**
     * 常量判断
     */
    public static final String XIAO_KONG="xiaokong";
    /**
     * 常量0
     */
    public static final String VALUE_0="0";
    /**
     * 常量1
     */
    public static final String VALUE_1="1";
    /**
     * 常量2
     */
    public static final String VALUE_2="2";
    /**
     * 常量3
     */
    public static final String VALUE_3="3";
    /**
     * 常量4
     */
    public static final String VALUE_4="4";
    /**
     * 常量5
     */
    public static final String VALUE_5="5";
    /**
     * 常量9
     */
    public static final String VALUE_9="9";
    /**
     * 10:待支付
     */
    public static final String VALUE_10="10";
    /**
     * 常量19
     */
    public static final String VALUE_19="19";
    /**
     * 20:待接单
     */
    public static final String VALUE_20="20";
    /**
     * 21:已接单
     */
    public static final String VALUE_21="21";
    /**
     * 30:备货中
     */
    public static final String VALUE_30="30";
    /**
     * 31:已备货
     */
    public static final String VALUE_31="31";
    /**
     * 40:待取餐
     */
    public static final String VALUE_40="40";
    /**
     * 41:已取餐
     */
    public static final String VALUE_41="41";
    /**
     * 50:待配送
     */
    public static final String VALUE_50="50";
    /**
     * 51:配送中
     */
    public static final String VALUE_51="51";
    /**
     * 52:已送达
     */
    public static final String VALUE_52="52";
    /**
     * 80:已完成
     */
    public static final String VALUE_80="80";
    /**
     * 已取消90
     */
    public static final String VALUE_90="90";
    /**
     * 常量5
     */
    public static final int INT_5=5;

    /**
     * 常量999
     */
    public static final String VALUE_999="999";
    /**
     * 图片存放位置
     */
    public static final String CAMERA_PATH = "/sdcard/DCIM/Camera/picture";
    /**
     * 家
     */
    public static final String HOME = "家";
    /**
     * 公司
     */
    public static final String COMPANY = "公司";
    /**
     * 学校
     */
    public static final String SCHOOL = "学校";
    /**
     * 先生
     */
    public static final String SIR = "先生";
    /**
     * 女士
     */
    public static final String LADY = "女士";

    public static final int INT_1=1;
    public static final int INT_2=2;
    public static final int INT_4=4;
    public static final int INT_8=8;
    public static final int INT_9=9;
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 重设 view 的宽高
     */
    public static void setViewLayoutParams(View view, int nWidth) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp.width != nWidth) {
            lp.width = nWidth;
            lp.height = nWidth;
            view.setLayoutParams(lp);
        }
    }
}
