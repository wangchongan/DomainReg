package com.jinmibao.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtil {

    /**
     * 将数组转为LIST
     * 
     * @param array
     * @return
     */
    public static List<String> array2List(String[] array) {
        return new ArrayList<String>(Arrays.asList(array));
    }

    /**
     * 移除数组里面的空串和空格
     * 
     * @param array
     * @return
     */
    public static String[] removeBlank(String[] array) {
        List<String> stringList = array2List(array);

        for (int i = 0; i < stringList.size();) {
            if (StringUtil.isBlank(String.valueOf(stringList.get(i)))) {
                stringList.remove(i);
                continue;
            }
            i++;
        }

        return ListUtil.list2Array(stringList);
    }

    /**
     * 把数组里面的元素以某个字符串拼接起来
     * 
     * @param strArray
     * @param joinFlag
     * @return
     */
    public static String joinArray2Str(String[] strArray, String joinFlag) {
        if (strArray == null || joinFlag == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (String str : strArray) {
            sb.append(str);
            sb.append(joinFlag);
        }

        return StringUtil.removeLastStr(sb.toString(), joinFlag.length());
    }

}
