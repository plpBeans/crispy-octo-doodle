package vip.hengnai.wine;


import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationQualityReport;

import org.greenrobot.eventbus.EventBus;

import cn.jpush.android.api.JPushInterface;
import vip.hengnai.wine.framework.retrofit.RetrofitManager;
import vip.hengnai.wine.util.MyMapUtils;
import vip.hengnai.wine.util.LocationUtils;

/**
 *
 * @author hua
 * @date 2019/8/21
 */

public class SellWineApplication extends Application {
    private RetrofitManager retrofitManager;
    public static SellWineApplication INSTANCE = null;
    public static SellWineApplication getInstance() {
        return INSTANCE;
    }
    public static boolean updateMessage=false;
    public static String getProvince="";
    public static String city="";
    public static String poiName="";
    public static double longitude=0.0000;
    public static double latitude=0.0000;
    public static String decId="";
    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        retrofitManager = new RetrofitManager(getApplicationContext());
        // 设置开启日志,发布时请关闭日志
        JPushInterface.setDebugMode(true);
        // 初始化 JPush
        JPushInterface.init(this);
        decId=JPushInterface.getRegistrationID(this);
        Log.e("极光id",JPushInterface.getRegistrationID(this));
        //初始化定位
        MyMapUtils.initLocation(this, locationListener);

    }


    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);

    }
    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {

                StringBuffer sb = new StringBuffer();
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if (location.getErrorCode() == 0) {
                    Log.e("定位结果","定位成功"+"定位类型"+location.getLocationType());
                    sb.append("定位成功" + "\n");
                    // 1 GPS定位结果 2 前次定位结果 4 缓存定位结果 5 Wifi定位结果 6 基站定位结果 8离线定位结果 9 最后位置缓存
                    sb.append("定位类型: " + location.getLocationType() + "\n");
                    sb.append("经    度    : " + location.getLongitude() + "\n");
                    sb.append("纬    度    : " + location.getLatitude() + "\n");
                    sb.append("精    度    : " + location.getAccuracy() + "米" + "\n");
                    sb.append("提供者    : " + location.getProvider() + "\n");

                    sb.append("速    度    : " + location.getSpeed() + "米/秒" + "\n");
                    sb.append("角    度    : " + location.getBearing() + "\n");
                    // 获取当前提供定位服务的卫星个数
                    sb.append("星    数    : " + location.getSatellites() + "\n");
                    sb.append("国    家    : " + location.getCountry() + "\n");
                    sb.append("省            : " + location.getProvince() + "\n");
                    sb.append("市            : " + location.getCity() + "\n");
                    sb.append("城市编码 : " + location.getCityCode() + "\n");
                    sb.append("区            : " + location.getDistrict() + "\n");
                    sb.append("区域 码   : " + location.getAdCode() + "\n");
                    sb.append("地    址    : " + location.getAddress() + "\n");
                    sb.append("兴趣点    : " + location.getPoiName() + "\n");
                    //定位完成的时间
                    sb.append("定位时间: " + LocationUtils.formatUTC(location.getTime(), "yyyy-MM-dd HH:mm:ss") + "\n");
                    sb.append("***定位质量报告***").append("\n");
                    sb.append("* WIFI开关：").append(location.getLocationQualityReport().isWifiAble() ? "开启" : "关闭").append("\n");
                    sb.append("* GPS状态：").append(getGPSStatusString(location.getLocationQualityReport().getGPSStatus())).append("\n");
                    sb.append("* GPS星数：").append(location.getLocationQualityReport().getGPSSatellites()).append("\n");
                    sb.append("* 网络类型：" + location.getLocationQualityReport().getNetworkType()).append("\n");
                    sb.append("* 网络耗时：" + location.getLocationQualityReport().getNetUseTime()).append("\n");
                    sb.append("****************").append("\n");
                    //定位之后的回调时间
                    sb.append("回调时间: " + LocationUtils.formatUTC(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "\n");
                    getProvince=location.getProvince();
                    city=location.getCity();
                    poiName=location.getPoiName();
                    longitude=location.getLongitude();
                    latitude=location.getLatitude();
                    //解析定位结果，
                    String result = sb.toString();
//                    Log.e("定位结果", result);

                } else {
                    //定位失败
                    sb.append("定位失败" + "\n");
                    sb.append("错误码:" + location.getErrorCode() + "\n");
                    sb.append("错误信息:" + location.getErrorInfo() + "\n");
                    sb.append("错误描述:" + location.getLocationDetail() + "\n");
                    sb.append("***定位质量报告***").append("\n");
                    sb.append("* WIFI开关：").append(location.getLocationQualityReport().isWifiAble() ? "开启" : "关闭").append("\n");
                    sb.append("* GPS状态：").append(getGPSStatusString(location.getLocationQualityReport().getGPSStatus())).append("\n");
                    sb.append("* GPS星数：").append(location.getLocationQualityReport().getGPSSatellites()).append("\n");
                    sb.append("* 网络类型：" + location.getLocationQualityReport().getNetworkType()).append("\n");
                    sb.append("* 网络耗时：" + location.getLocationQualityReport().getNetUseTime()).append("\n");
                    sb.append("****************").append("\n");
                    //定位之后的回调时间
                    sb.append("回调时间: " + LocationUtils.formatUTC(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "\n");

                    //解析定位结果，
                    String result = sb.toString();
                    Log.e("定位结果", result);
                }
            EventBus.getDefault().post(location);
            } else {
//                tvResult.setText("定位失败，loc is null");
                Log.e("定位结果", "定位失败");
                EventBus.getDefault().post(location);
            }
//          MyMapUtils.stopLocation();
        }
    };
    /**
     * 获取GPS状态的字符串
     *
     * @param statusCode GPS状态码
     * @return
     */
    private String getGPSStatusString(int statusCode) {
        String str = "";
        switch (statusCode) {
            case AMapLocationQualityReport.GPS_STATUS_OK:
                str = "GPS状态正常";
                break;
            case AMapLocationQualityReport.GPS_STATUS_NOGPSPROVIDER:
                str = "手机中没有GPS Provider，无法进行GPS定位";
                break;
            case AMapLocationQualityReport.GPS_STATUS_OFF:
                str = "GPS关闭，建议开启GPS，提高定位质量";
                break;
            case AMapLocationQualityReport.GPS_STATUS_MODE_SAVING:
                str = "选择的定位模式中不包含GPS定位，建议选择包含GPS定位的模式，提高定位质量";
                break;
            case AMapLocationQualityReport.GPS_STATUS_NOGPSPERMISSION:
                str = "没有GPS定位权限，建议开启gps定位权限";
                break;
        }
        return str;
    }
}
