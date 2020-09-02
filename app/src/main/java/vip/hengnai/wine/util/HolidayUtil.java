package vip.hengnai.wine.util;

import java.util.Arrays;
import java.util.List;

/**
 * @author Hugh
 */
public class HolidayUtil {


    /**
     * 根据国历获取假期
     *
     * @return
     */
    public static String getSolarHoliday(int solarYear, int solarMonth, int solarDay) {
        String message = "";
        int value2=2;
        int value3=3;
        int value14=14;
        int value8=8;
        int value12=12;
        int value15=15;
        int value4=4;
        int value6=6;
        int value1999=1999;
        int value5=5;
        int value7=7;
        int value9=9;
        int value10=10;
        if (solarMonth == 1 && solarDay == 1) {
            message = "元旦";
        } else if (solarMonth == value2 && solarDay == value14) {
            message = "情人节";
        } else if (solarMonth == value3 && solarDay == value8) {
            message = "妇女节";
        } else if (solarMonth == value3 && solarDay == value12) {
            message = "植树节";
        } else if (solarMonth == value3 && solarDay == value15) {
            message = "消费者";
        } else if (solarMonth == value4) {

            if (solarDay == 1) {
                message = "愚人节";
            } else if (solarDay >= value4 && solarDay <= value6) {
                if (solarYear <= value1999) {
                    int compare = (int) (((solarYear - 1900) * 0.2422 + 5.59) - ((solarYear - 1900) / 4));
                    if (compare == solarDay) {
                        message = "清明节";
                    }
                } else {
                    int compare = (int) (((solarYear - 2000) * 0.2422 + 4.81) - ((solarYear - 2000) / 4));
                    if (compare == solarDay) {
                        message = "清明节";
                    }
                }
            }
        } else if (solarMonth == value5 && solarDay == 1) {
            message = "劳动节";
        } else if (solarMonth == value5 && solarDay == value4) {
            message = "青年节";
        } else if (solarMonth == value6 && solarDay == 1) {
            message = "儿童节";
        } else if (solarMonth == value7 && solarDay == 1) {
            message = "建党节";
        } else if (solarMonth == value8 && solarDay == 1) {
            message = "建军节";
        } else if (solarMonth == value9 && solarDay == value10) {
            message = "教师节";
        } else if (solarMonth == value10 && solarDay == 1) {
            message = "国庆节";
        }
        return message;
    }


    /**
     * 用于获取中国的传统节日
     *
     * @param lunarMonth 农历的月
     * @param lunarDay   农历日
     * @return 中国传统节日
     */
    public static String getLunarHoliday(int lunarYear, int lunarMonth, int lunarDay) {
        String message = "";
        int value2=2;
        int value15=15;
        int value5=5;
        int value7=7;
        int value8=8;
        int value9=9;
        int value12=12;
        int value23=23;
        if (lunarMonth == 1 && lunarDay == 1) {
            message = "春节";
        } else if (lunarMonth == 1 && lunarDay == value15) {
            message = "元宵节";
        } else if (lunarMonth == value2 && lunarDay == value2) {
            message = "龙抬头";
        } else if (lunarMonth == value5 && lunarDay == value5) {
            message = "端午节";
        } else if (lunarMonth == value7 && lunarDay == value7) {
            message = "七夕";
        } else if (lunarMonth == value7 && lunarDay == value15) {
            message = "中元节";
        } else if (lunarMonth == value8 && lunarDay == value15) {
            message = "中秋节";
        } else if (lunarMonth == value9 && lunarDay == value9) {
            message = "重阳节";
        } else if (lunarMonth == value12 && lunarDay == value8) {
            message = "腊八节";
        } else if (lunarMonth == value12 && lunarDay == value23) {
            message = "小年";
        } else {
            if (lunarMonth == value12) {
                int value29=29;
                int value30=30;
                if ((((LunarUtil.daysInLunarMonth(lunarYear, lunarMonth) == value29) && lunarDay == value29))
                        || ((((LunarUtil.daysInLunarMonth(lunarYear, lunarMonth) == value30 ) && lunarDay == value30)))) {
                    message = "除夕";
                }
            }
        }
        return message;
    }

    /**
     * 法定节假日 休息的日期
     */
    public static List<String> holidayList = Arrays.asList("2017-12-30", "2017-12-31", "2018-01-01", "2018-02-15", "2018-02-16", "2018-02-17", "2018-02-18",
            "2018-02-19", "2018-02-20", "2018-02-21", "2018-04-05", "2018-04-06", "2018-04-07", "2018-04-29", "2018-04-30", "2018-05-01", "2018-06-16", "2018-06-17",
            "2018-06-18", "2018-09-22", "2018-09-23", "2018-09-24", "2018-10-01", "2018-10-02", "2018-10-03", "2018-10-04", "2018-10-05", "2018-10-06", "2018-10-07",
            "2018-12-30", "2018-12-31", "2019-01-01", "2019-02-04", "2019-02-05", "2019-02-06", "2019-02-07", "2019-02-08", "2019-02-09", "2019-02-10", "2019-04-05", "2019-04-06",
            "2019-04-07", "2019-05-01", "2019-05-02", "2019-05-03", "2019-05-04", "2019-06-07", "2019-06-08", "2019-06-09", "2019-09-13", "2019-09-14", "2019-09-15", "2019-10-01", "2019-10-02",
            "2019-10-03", "2019-10-04", "2019-10-05", "2019-10-06", "2019-10-07");

    /**
     * 补班的日期
     */
    public static List<String> workdayList = Arrays.asList("2018-02-11", "2018-02-24", "2018-04-08", "2018-04-28", "2018-09-29", "2018-04-30", "2018-12-29",
            "2019-02-02", "2019-02-03", "2019-04-28", "2019-05-05", "2019-09-29", "2019-10-12");


}
