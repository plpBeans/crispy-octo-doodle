package vip.hengnai.wine.util;

import vip.hengnai.wine.Constants;

/**
 * on 2020/3/1.
 *
 * @author hua.
 */

public class TimeUtil {
    /**时分秒格式00:00:00转换秒数
     * @param time 			//时分秒格式00:00:00
     * @return  秒数
     */
    public static long getSecond(String time){
        long s = 0;
        if(Constants.INT_8==time.length()){ //时分秒格式00:00:00
            int index1=time.indexOf(":");
            int index2=time.indexOf(":",index1+1);
            s = Integer.parseInt(time.substring(0,index1))*3600;//小时
            s+=Integer.parseInt(time.substring(index1+1,index2))*60;//分钟
            s+=Integer.parseInt(time.substring(index2+1));//秒
        }else if(Constants.INT_5==time.length()){
            //分秒格式00:00
            s = Integer.parseInt(time.substring(time.length()-2)); //秒  后两位肯定是秒
            s+=Integer.parseInt(time.substring(0,2))*60;    //分钟
        }else{
            s = Integer.parseInt(time); //秒  后两位肯定是秒
        }
        return s;
    }
}
