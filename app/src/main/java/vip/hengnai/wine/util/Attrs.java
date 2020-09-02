package vip.hengnai.wine.util;


/**
 * @author Hugh
 */
public class Attrs {


    //指示圆点的位置
    /**
     * 在公历日期上面
     */
    public static final int UP = 200;
    /**
     * 在公历日期下面
     */
    public static final int DOWN = 201;

    //周的第一天
    /**
     * 周的第一天 周日
     */
    public static final int SUNDAY = 300;
    /**
     * 周的第一天 周一
     */
    public static final int MONDAY = 301;


    //节假日的位置
    /**
     * 右上方
     */
    public static final int TOP_RIGHT = 400;
    /**
     * 左上方
     */
    public static final int TOP_LEFT = 401;
    /**
     * 右下方
     */
    public static final int BOTTOM_RIGHT = 402;
    /**
     * 左下方
     */
    public static final int BOTTOM_LEFT = 403;
    public int lunarTextColor;
    public int solarHolidayTextColor;
    public int lunarHolidayTextColor;
    public int solarTermTextColor;
    public int selectCircleColor;
    /**
     * 选中公历颜色
     */
    public int selectSolarTextColorColor;
    /**
     * 选中农历颜色
     */
    public int selectLunarTextColor;
    public float solarTextSize;
    public float lunarTextSize;
    /**
     * 农历到文字中心的距离
     */
    public float lunarDistance;
    public float selectCircleRadius;
    public boolean isShowLunar;


    public float pointSize;
    /**
     * 圆点到文字中心的距离
     */
    public float pointDistance;
    public int pointColor;
    /**
     * 0 在上面 1在下面
     */
    public int pointLocation;
    public int hollowCircleColor;
    public float hollowCircleStroke;

    public int firstDayOfWeek;
    public int defaultCalendar;
    /**
     * 正常日历的高度
     */
    public int calendarHeight;
    /**
     * 拉伸后日历的高度
     */
    public int stretchCalendarHeight;
    public int duration;

    public boolean isShowHoliday;
    public int holidayColor;
    public float holidayTextSize;
    public float holidayDistance;
    public int holidayLocation;
    public int workdayColor;
    /**
     * 不在同一月的颜色透明度
     */
    public int alphaColor;
    /**
     * 不可用的日期颜色透明度
     */
    public int disabledAlphaColor;
    /**
     * 点击不可用的日期提示语
     */
    public String disabledString;

    /**
     * 当天被选中的对比色，选中当前的农历，原定等颜色
     */
    public int todaySelectContrastColor;
    /**
     * 日历背景
     */
    public int bgCalendarColor;
    /**
     * 拉伸显示的字体大小
     */
    public float stretchTextSize;
    /**
     * 拉伸显示的字体颜色
     */
    public int stretchTextColor;
    /**
     * 拉伸显示的字体距离矩形中心的距离
     */
    public float stretchTextDistance;
    /**
     * 月是否都6行
     */
    public boolean isAllMonthSixLine;


}
