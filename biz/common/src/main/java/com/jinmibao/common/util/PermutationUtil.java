/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.jinmibao.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列组合
 * 
 * @author chongan.wangca 2012-4-27 上午2:14:22
 */
public class PermutationUtil {

    List<String> permList = new ArrayList<String>();

    public String[] dealPerm(String orginal) {
        ArrayList<String> list = new ArrayList<String>();
        if (orginal.length() == 1) {
            permList.add(orginal);
            return new String[] { orginal };
        } else {
            for (int i = 0; i < orginal.length(); i++) {
                String s = orginal.charAt(i) + "";
                String result = "";
                String resultA = result + s;
                String leftS = orginal.substring(0, i) + orginal.substring(i + 1, orginal.length());
                for (String element : dealPerm(leftS)) {
                    result = resultA + element;
                    list.add(result);
                    permList.add(result);
                    // System.out.println(result);
                }
            }
            return (String[]) list.toArray(new String[list.size()]);
        }
    }

    public String[] dealPerm(String orginal, int lengthStart, int lengthEnd) {
        ArrayList<String> list = new ArrayList<String>();
        if (orginal.length() == 1) {
            if (orginal.length() >= lengthStart && orginal.length() <= lengthEnd) {
                permList.add(orginal);
            }
            return new String[] { orginal };
        } else {
            for (int i = 0; i < orginal.length(); i++) {
                String s = orginal.charAt(i) + "";
                String result = "";
                String resultA = result + s;
                String leftS = orginal.substring(0, i) + orginal.substring(i + 1, orginal.length());
                for (String element : dealPerm(leftS)) {
                    result = resultA + element;
                    list.add(result);
                    if (result.length() >= lengthStart && result.length() <= lengthEnd) {
                        permList.add(result);
                    }
                    // System.out.println(result);
                }
            }
            return (String[]) list.toArray(new String[list.size()]);
        }
    }

    public List<String> getPermList() {
        return permList;
    }

}
