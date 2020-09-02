package vip.hengnai.wine.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * AUTHOR：dell
 * TIME:2019/9/5 2019/9/5
 * DESCRIPTION:NotNull
 * @author dell
 */
public class NotNull {

    private static int INT_NOT_NULL=0;
    private static String NOT_NAN="NaN";

    public static boolean isNotNull(Integer i) {
        if (null != i && INT_NOT_NULL != i) {
            return true;
        }
        return false;
    }

    public static boolean isNotNull(Double d) {
        if (null != d && INT_NOT_NULL != d) {
            return true;
        }
        return false;
    }

    public static boolean isNotNull(Object object) {
        if (null != object && !"".equals(object)) {
            return true;
        }
        return false;
    }

    public static boolean isNotNull(List<?> t) {
        if (null != t && t.size() > INT_NOT_NULL) {
            return true;
        }
        return false;
    }

    public static boolean isNotNull(Map<?, ?> t) {
        if (null != t && t.size() > INT_NOT_NULL) {
            return true;
        }
        return false;
    }

    public static boolean isNotNull(Object[] objects) {
        if (null != objects && objects.length > INT_NOT_NULL) {
            return true;
        }
        return false;
    }

    public static boolean isNotNull(JSONArray jsonArray) {
        if (null != jsonArray && jsonArray.length() > INT_NOT_NULL) {
            return true;
        }
        return false;
    }

    public static boolean isNotNull(JSONObject jsonObject) {
        if (null != jsonObject && !"".equals(jsonObject)) {
            return true;
        }
        return false;
    }

    public static boolean isNotNullAndNan(Object object) {
        if (isNotNull(object) && !NOT_NAN.equals(object.toString())) {
            return true;
        }
        return false;
    }

    /**
     *
     * 方法描述<判断多个字符串是否有空值>
     * @version 1.0
     * @createTime 2015年11月6日,上午10:06:00
     * @updateTime
     * @updateAuthor
     * @updateInfo (此处输入修改内容,若无修改可不写.)
     * @param strings
     * @return
     */
    public static boolean isNotNull(String... strings) {
        for (int i = 0; i < strings.length; i++) {
            if (null == strings[i] || "".equals(strings[i])) {
                return false;
            }
        }
        return true;
    }
}
