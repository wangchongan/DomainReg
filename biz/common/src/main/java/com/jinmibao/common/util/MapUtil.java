package com.jinmibao.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapUtil {

    public static String joinMap2Str(Map<String, String> map, String joinBy) {
        if (map == null || map.size() <= 0) {
            return "";
        }
        if (StringUtil.isBlank(joinBy)) {
            joinBy = ",";
        }
        Set<Map.Entry<String, String>> set = map.entrySet();
        Iterator<Map.Entry<String, String>> it = set.iterator();

        StringBuffer sb = new StringBuffer();

        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            sb.append(entry.getValue().toString());
            sb.append(joinBy);
        }
        return StringUtil.removeLastStr(sb.toString(), joinBy.length());
    }

    /**
     * 把字符串劈串放到Map中
     * 
     * @param str
     * @param joinBy
     * @return
     */
    public static Map<String, String> str2Map(String str, String joinBy) {
        String[] strArray = str.split(joinBy);
        Map<String, String> map = new HashMap<String, String>();
        if (strArray != null && strArray.length > 0) {
            for (String value : strArray) {
                map.put(value, value);
            }
        }
        return map;
    }

    public static void main(String[] args) {
    }
}
