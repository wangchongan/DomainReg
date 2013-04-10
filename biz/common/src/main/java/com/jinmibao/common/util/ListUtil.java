package com.jinmibao.common.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {

    /**
     * 把LIST转为数组
     * 
     * @param list
     * @return
     */
    public static String[] list2Array(List<String> list) {
        return (String[]) list.toArray(new String[list.size()]);
    }

    /**
     * 判断str的indexOf List中的每个String,是否存在不为-1的
     * 
     * @param str 如"ORACLE10g"
     * @param keysList "ORACLE","MYSQL" 以上例子会返回匹配上的字符串
     * @return
     */
    public static String strContainKeyInList(String str, List<String> keysList) {
        if (keysList == null || str == null) {
            return null;
        }
        for (String key : keysList) {
            if (str.indexOf(key) != -1) {
                return key;
            }
        }
        return null;
    }

    public static boolean containStr(String str, List<String> keysList) {
        if (keysList == null || str == null) {
            return false;
        }
        for (String key : keysList) {
            if (str.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 把LIST里面的字符串根据指定符号拼成串
     * 
     * @param strList
     * @param joinFlag
     * @return
     */
    public static String joinList2Str(List<String> strList, String joinFlag) {
        if (strList == null || joinFlag == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (String str : strList) {
            sb.append(str);
            sb.append(joinFlag);
        }

        return StringUtil.removeLastStr(sb.toString(), joinFlag.length());
    }

    /**
     * 解析截取字符串，返回LIST
     * 
     * @param str
     * @param join
     * @return
     */
    public static List<Long> str2LongList(String str, String join) {
        if (str == null || join == null) {
            return null;
        }
        String[] strArray = str.split(join);
        List<Long> list = new ArrayList<Long>();
        for (String s : strArray) {
            if (!StringUtil.isBlank(s)) {
                list.add(Long.valueOf(s));
            }
        }
        return list;
    }

    /**
     * 解析截取字符串，返回LIST
     * 
     * @param str
     * @param join
     * @return
     */
    public static List<String> str2StringList(String str, String join) {
        if (str == null || join == null) {
            return null;
        }
        String[] strArray = str.split(join);
        List<String> list = new ArrayList<String>();
        for (String s : strArray) {
            if (!StringUtil.isBlank(s)) {
                list.add(s);
            }
        }
        return list;
    }

    /**
     * 把list里面的字符串再拆分得到新的 LIST 适用于这样的字符串： 4 5 6 | 9 8 7 | 09 76
     * 
     * @param list
     * @param split
     * @return
     */
    public static List<List<String>> list2List(List<String> list, String split) {

        List<List<String>> listList = new ArrayList<List<String>>();
        if (list == null || list.size() < 1 || split == null) {
            return null;
        }

        for (String str : list) {
            List<String> strList = str2StringList(str, split);
            listList.add(strList);
        }

        return listList;
    }

    /**
     * 计算总个数
     * 
     * @param list
     * @return
     */
    public static int listWordCount(List<List<String>> list) {
        int count = 0;
        for (List<String> myList : list) {
            count = count + myList.size();
        }
        return count;
    }
}
