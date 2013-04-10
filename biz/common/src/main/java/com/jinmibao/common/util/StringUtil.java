package com.jinmibao.common.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import com.jinmibao.common.constants.RegExpConstant;

/**
 * 系统公共工具类
 * 
 * @author wangchongan
 */
public class StringUtil {

    private final static Logger log = Logger.getLogger(StringUtil.class);

    /*
     * commons小组的MD5加密方法
     */
    public final static String commonsMD5(String s) {
        return DigestUtils.md5Hex(s);
    }

    /**
     * 空值转空串
     * 
     * @param s
     * @return
     */
    public static String null2space(String s) {
        return s == null ? "" : s;
    }

    /**
     * 将不为整数的字符串换为传来的替代值
     * 
     * @param str
     * @param defaultNum
     * @return
     */
    public static String noNum2DefaultNum(String str, String defaultNum) {
        String newStr = (str == null || (str != null && !str.matches("^[0-9]*[1-9][0-9]*$"))) ? defaultNum : str;
        return newStr;
    }

    /**
     * 重载 将不为整数的字符串换为"1"
     * 
     * @param str
     * @return
     */
    public static String noNum2DefaultNum(String str) {
        String newStr = (str == null || (str != null && !str.matches("^[0-9]*[1-9][0-9]*$"))) ? "1" : str;
        return newStr;
    }

    /**
     * 月份转季度
     * 
     * @param month
     * @return
     */
    public static String monthToQuarter(String month) {
        String s = "";
        if (month != null && !month.equals("")) {
            s = "" + ((Integer.parseInt(month) - 1) / 3 + 1);
        }
        return s;
    }

    public static String empty2Zero(String str) {
        return str != null && str.equals("") ? "0" : str;
    }

    public static String null2Zero(String str) {
        return str == null ? "0" : str;
    }

    /**
     * 计算相差天数
     * 
     * @param args
     */
    public static String getDays(String d1, String d2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        NumberFormat nf = NumberFormat.getInstance();
        long time = 0;
        try {
            if (d1 != null && d2 != null) {
                time = df.parse(d2).getTime() - df.parse(d1).getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long hours = time / (60 * 60 * 1000);
        return nf.format((hours / 24 + (hours % 24 >= 4 ? 0.5 : 0)));
    }

    public static String arrayToString(String[] str) {
        String ret = "";
        if (str != null) {
            for (String s : str) {
                ret += s + ",";
            }
        }
        return ret.indexOf(",") > -1 ? ret.substring(0, ret.lastIndexOf(",")) : "";
    }

    /**
     * 自动生成一个数字和字母的字符串
     * 
     * @param length 取得的长度
     */
    public final static String getRandStr(int length) {
        String val = "";

        Random random = new Random(Math.round(Math.random() * 100000000));
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

            if ("char".equalsIgnoreCase(charOrNum)) // 字符串
            {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) // 数字
            {
                val += String.valueOf(random.nextInt(10));
            }
        }

        return val;
    }

    // 随机数
    private static long randLong;
    private static int  randInt;

    /**
     * 返回大于等于start 且小于 end 的随机数
     * 
     * @param start 开始
     * @param eqStart 是否允许等于
     * @param end 结束
     * @param eqEnd 是否允许等于
     * @return
     */
    public static long getRandLong(long start, boolean eqStart, long end, boolean eqEnd) {
        randLong = Math.round(Math.random() * (end - start) + start);
        if (!eqStart && randLong == start) {
            getRandLong(start, eqStart, end, eqEnd);
        }
        if (!eqEnd && randLong == end) {
            getRandLong(start, eqStart, end, eqEnd);
        }
        return randLong;
    }

    /**
     * 返回大于等于start 且小于 end 的随机数
     * 
     * @param start 开始
     * @param eqStart 是否允许等于
     * @param end 结束
     * @param eqEnd 是否允许等于
     * @return
     */
    public static int getRandInt(int start, boolean eqStart, int end, boolean eqEnd) {
        randInt = Long.valueOf(Math.round(Math.random() * (end - start) + start)).intValue();
        if (!eqStart && randInt == start) {
            getRandInt(start, eqStart, end, eqEnd);
        }
        if (!eqEnd && randInt == end) {
            getRandInt(start, eqStart, end, eqEnd);
        }
        return randInt;
    }

    // 统计一个字符串里含有某个字符的个数,注意是一个字符
    public static int countCharNumber(String s, char c) {
        char[] chars = s.toCharArray();
        int num = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == c) num++;
        }
        return num;
    }

    // 统计一个字符串里含有某个字符串的个数
    public static int countStrNumber(String inStr, String str) {
        int n = 0, index;
        index = inStr.indexOf(str);
        while (index >= 0) {
            inStr = inStr.substring(index + 1);
            index = inStr.indexOf(str);
            n++;
        }
        return n;
    }

    // 解码
    public static String urlDecoder(String str) {
        String newStr = "";
        try {
            newStr = URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return newStr;
    }

    public static Integer stringToInteger(String str) {
        return Integer.valueOf(str);
    }

    /**
     * 根据类路径，获取CLASS对象
     * 
     * @param classPathStr 完整类路径
     * @return
     */
    public static Class makeClass(String classPathStr) {
        Class aClass = null;
        try {
            aClass = Class.forName(classPathStr);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return aClass;
    }

    /**
     * 将double转换成long
     * 
     * @param val
     * @return
     */
    public static long double2Long(double val) {
        return Math.round(val);
    }

    /**
     * 判断不是NULL也不是空串
     * 
     * @param str
     * @return
     */
    public static boolean isNotNullAndNotEmptyStr(String str) {
        if (str != null && !"".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是NULL 或 空串
     * 
     * @param str
     * @return
     */
    public static boolean isNullOrEmptyStr(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是NULL 或 空串
     * 
     * @param str
     * @return
     */
    public static boolean isNullOrEmptyStr(String... str) {
        for (String s : str) {
            if (isNullOrEmptyStr(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 通过反射机制去实例化特定构造器的类
     * 
     * @param classPath 类路径
     * @param ca 定义参数类型的数组
     * @param oa 定义字段的值的数组
     * @return
     * @throws Exception
     */
    public static Object getObjByReflect(String classPath, Class[] paramsTypeArray, Object[] paramsValueArray)
                                                                                                              throws Exception {

        Class c = Class.forName(classPath);
        Class[] classArray = paramsTypeArray;
        Constructor con = c.getConstructor(classArray);
        Object[] o = paramsValueArray;
        return con.newInstance(o);

    }

    /**
     * @param fieldName
     * @param fieldValue
     * @param type
     * @param valType 0 -字符型 1-数字型
     * @return
     */
    public static String getWherePart(String fieldName, String fieldValue, String type, int valType) {
        String oType = type;

        // 如果没有指定操作类型，则默认为等于
        if (isNullOrEmptyStr(type)) {
            oType = "=";
        } else if ("like".equals(type)) {
            oType = "like";
        } else {
            oType = type;
        }

        // 如果非法数值 则默认为0
        if (valType != 0 && valType != 1) {
            valType = 0;

        }

        if (isNotNullAndNotEmptyStr(fieldValue)) {
            // 如果是等于的情况
            if ("=".equals(oType)) {
                if (valType == 0) {
                    return " and " + fieldName + " = '" + fieldValue + "' ";
                } else {
                    return " and " + fieldName + " = " + fieldValue + " ";
                }
            }
            // 如果是like情况
            else if ("like".equals(oType)) {
                return " and " + fieldName + " like '%" + fieldValue + "%' ";
            }
            // 如果是其他情况
            else {
                if (valType == 0) {
                    return " and " + fieldName + " " + type + " '" + fieldValue + "' ";
                } else {
                    return " and " + fieldName + " " + type + " " + fieldValue + " ";
                }
            }
        } else {
            return " ";
        }
    }

    /**
     * 默认为字符串操作的时候 重载方法
     * 
     * @param fieldName
     * @param fieldValue
     * @param type
     * @return
     */
    public static String getWherePart(String fieldName, String fieldValue, String type) {
        String oType = type;

        // 如果没有指定操作类型，则默认为等于
        if (isNullOrEmptyStr(type)) {
            oType = "=";
        } else if ("like".equals(type)) {
            oType = "like";
        } else {
            oType = type;
        }

        if (isNotNullAndNotEmptyStr(fieldValue)) {
            // 如果是等于的情况
            if ("=".equals(oType)) {
                return " and " + fieldName + " = '" + fieldValue + "' ";
            }
            // 如果是like情况
            else if ("like".equals(oType)) {
                return " and " + fieldName + " like '%" + fieldValue + "%' ";
            }
            // 如果是其他情况
            else {
                return " and " + fieldName + " " + type + " '" + fieldValue + "' ";
            }
        } else {
            return " ";
        }
    }

    /**
     * 等于的情况 重载方法
     * 
     * @param fieldName
     * @param fieldValue
     * @return
     */
    public static String getWherePart(String fieldName, String fieldValue) {
        String oType = "=";
        if (isNotNullAndNotEmptyStr(fieldValue)) {
            return " and " + fieldName + " = '" + fieldValue + "' ";
        } else {
            return " ";
        }
    }

    /**
     * 获取拼成的串，并返回 如 1,3,4,5,67
     * 
     * @param valList 字符List
     * @param type 0-字符型 1-数值型 字符型带'号 数值型不带
     * @param splitChar 分隔符
     * @return
     */
    public static String getJoinStr(List valList, int type, String splitChar) {
        String str = "";
        for (int i = 0; i < valList.size(); i++) {
            if (i != (valList.size() - 1)) {
                // 数值型
                if (type == 1) {
                    str = str + valList.get(i) + splitChar;
                } else { // 字符串型
                    str = str + "'" + valList.get(i) + "'" + splitChar;
                }
            } else {
                // 数值型
                if (type == 1) {
                    str = str + valList.get(i);
                } else { // 字符串型
                    str = str + "'" + valList.get(i) + "'";
                }
            }
        }
        return str;
    }

    /**
     * 求两个时间相差的秒数
     * 
     * @param earlierDate
     * @param lateDate
     * @return
     */
    public static long getTwoDateDiffSeconds(Date earlierDate, Date lateDate) {
        long t = lateDate.getTime() - earlierDate.getTime();
        long s = t / 1000;
        return s;
    }

    /**
     * 该类用于获取properties资源文件对象 用法： 属性文件的路径为SRC/properties/bgPageSize.properties ResourceBundle rb =
     * GetBundle.getResourceBundle("properties.bgPageSize"); String ps = rb.getString("mbg-saleGoodsSearchList");
     */
    public static ResourceBundle getPropertiesObj(String name) {
        ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(name);
        return RESOURCE_BUNDLE;
    }

    /**
     * 从资源文件中获取某个key的值
     * 
     * @param path
     * @param key
     * @return
     */
    public static String getPropertiesValue(String path, String key) {
        ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(path);
        return RESOURCE_BUNDLE.getString(key);
    }

    /**
     * 从资源文件中获取某个key的值
     * 
     * @param path
     * @param key
     * @return
     */
    public static String getPropertiesValue(String key) {
        String path = "SysCoreConfig";
        ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(path);
        return RESOURCE_BUNDLE.getString(key);
    }

    // 验证两个对象是否是一个对象
    public static boolean checkObjTheSame(Object obj1, Object obj2) {
        if (obj1 == obj2) {
            return true;
        } else {
            return false;
        }
    }

    // 验证两个integer的int值是否相等
    public static boolean checkIntegerIntValueEqual(Integer val1, Integer val2) {
        if (val1.intValue() == val2.intValue()) {
            return true;
        } else {
            return false;
        }
    }

    // 验证两个字符串值是否相等
    public static boolean checkStringEqual(String str1, String str2) {
        if (str1.equals(str2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 有待根据以下资料进行改进： 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值 究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。如： X-Forwarded-For：192.168.1.110， 192.168.1.120， 192.168.1.130，
     * 192.168.1.100 用户真实IP为： 192.168.1.110
     * 
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /*
     * 获取ACTION访问的URL的真实地址，包括参数的，非常核心！！！
     */
    public static String getRequestURL(HttpServletRequest request) {

        if (request == null) {
            return "";
        }

        String url = "";
        url = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        url = url + request.getServletPath();
        Enumeration<String> names = request.getParameterNames();
        int i = 0;
        String requestPageUrl = "";
        if (names != null) {
            while (names.hasMoreElements()) {
                String name = (String) names.nextElement();
                if (name.equals("requestPageUrl")) {
                    requestPageUrl = request.getParameter(name);
                    continue;
                }
                if (i == 0) {
                    url = url + "?";
                } else {
                    url = url + "&";
                }
                i++;
                String value = request.getParameter(name);
                if (value == null) {
                    value = "";
                }
                url = url + name + "=" + value;
            }
        }
        try {
            url = java.net.URLEncoder.encode(url, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url; // 将会返回http://localhost:port/projectName/myPhoto.do?id=888 这样的串
    }

    /*
     * 获取ACTION访问的URL的真实地址，不包括参数
     */
    public static String getRequestURLNoParams(HttpServletRequest request) {

        if (request == null) {
            return "";
        }

        String url = "";
        url = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        url = url + request.getServletPath();
        try {
            url = java.net.URLEncoder.encode(url, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url; // 将会返回http://localhost:port/projectName/myPhoto.do?id=888 这样的串
    }

    /**
     * MD5加密
     * 
     * @param str
     * @return
     */
    public static String MD5(String str) {
        if (str != null) {
            return DigestUtils.md5Hex(str);
        } else {
            return null;
        }
    }

    /**
     * @param str 加密串
     * @param saltFigure 盐值
     * @return
     */
    public static String MD5(String str, String saltFigure) {
        if (str != null) {
            if (saltFigure == null) {
                return MD5(str);
            } else {
                return MD5(str + saltFigure);
            }
        } else {
            return null;
        }
    }

    /**
     * 将一个字符串的首字母换成小写
     * 
     * @param str
     * @return
     */
    public static String firstLetterLower(String str) {
        if (str != null && str.length() > 0) {
            if (str.length() == 1) {
                return str.toLowerCase();
            } else {
                return str.substring(0, 1).toLowerCase() + str.substring(1);
            }
        } else {
            return str;
        }
    }

    /**
     * 将一个字符串的首字母换成大写
     * 
     * @param str
     * @return
     */
    public static String firstLetterUpper(String str) {
        if (str != null && str.length() > 0) {
            if (str.length() == 1) {
                return str.toLowerCase();
            } else {
                return str.substring(0, 1).toUpperCase() + str.substring(1);
            }
        } else {
            return str;
        }
    }

    /**
     * 把一个字符串，根据以下规则进行替换后返回替换后的新字符串。 规则：遍历Map,如果字符串中含有key串，则使用Map的value进行替换 Example: Map<String,String> tagsMap = new
     * HashMap<String, String>(); tagsMap.put("#1#", "替换的内容"); replaceFromMap("abcd#1#", tagsMap); **return "abcd替换的内容";
     * 
     * @param str 目标字符串
     * @param replaceMap 封装要替换的规则的Map
     * @return
     */
    public static String replaceFromMap(String str, Map replaceMap) {
        Iterator<Map.Entry<String, String>> it = replaceMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = it.next();
            String key = (String) entry.getKey();
            if (str.indexOf(key) != -1) {
                str = str.replaceAll(key, (String) entry.getValue());
            }
        }
        return str;
    }

    /**
     * 获取某个文本文件一共的行数
     * 
     * @param filePath 文件的全路径
     * @return
     */
    public static int getFileLineNumber(String filePath) {

        File test = new File(filePath);
        long fileLength = test.length();
        int lines = 0;
        LineNumberReader rf = null;
        try {
            rf = new LineNumberReader(new FileReader(test));
            if (rf != null) {
                rf.skip(fileLength);
                lines = rf.getLineNumber();
                rf.close();
            }
        } catch (IOException e) {
            if (rf != null) {
                try {
                    rf.close();
                } catch (IOException ee) {
                }
            }
        } finally {
            return lines + 1;
        }
    }

    // 符合查询的文件
    private static List<File> fileList = new ArrayList<File>();

    /**
     * 递归检索出该目录下所有的某几种类型的文件
     * 
     * @param f 根目录的文件
     * @param fileType 文件类型，如 new String[]{".txt",".java"}
     * @return
     */
    public static List<File> getAllFileRec(File f, String[] fileType) {
        if (f.isDirectory()) {
            File[] fs = f.listFiles();
            for (int i = 0; i < fs.length; i++) {
                getAllFileRec(fs[i], fileType);
            }
        } else {
            if (checkFileType(f, fileType)) {
                fileList.add(f);
            }
        }
        return fileList;
    }

    /**
     * 判断文件是否是某种类型的文件
     * 
     * @param f
     * @param fileType 文件类型，如 new String[]{".txt",".java"}
     * @return
     */
    public static boolean checkFileType(File f, String[] fileType) {
        String name = f.getName();
        for (int i = 0; i < fileType.length; i++) {
            if (name.endsWith(fileType[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据路径递归新建文件夹
     * 
     * @param dirPath 如：c:/1/2/3/4/5
     */
    public static void makeDirRec(String dirPath) {
        dirPath = dirPath.replaceAll("\\\\", "/");
        String[] dirArray = dirPath.split("/");
        String pathStr = dirArray[0];
        for (int i = 1; i < dirArray.length; i++) {
            if (!"".equals(dirArray[i])) {
                pathStr = pathStr + "/" + dirArray[i];
                if (!new File(pathStr).exists()) {
                    new File(pathStr).mkdir();
                    if (!new File(pathStr).exists()) {
                        System.out.println("路径 " + dirPath + " 不正确，无法新建文件夹，请核对。");
                        return;
                    }
                }
            }
        }
    }

    /**
     * 获取文件的名字，不包含类型
     * 
     * @param file
     * @return
     */
    public static String getFileName(File file) {
        String name = file.getName().replaceAll("\\.{1}.+", "");
        return name;
    }

    /**
     * 获取文件的类型
     * 
     * @param file
     * @return
     */
    public static String getFileType(File file) {
        int dot = file.getName().indexOf(".");
        return file.getName().substring(dot);
    }

    /**
     * 获取文件的名字，不包含类型
     * 
     * @param file 如：getFileName("c:/file4.java")
     * @return 如：file4
     */
    public static String getFileName(String filePath) {
        File file = new File(filePath);
        String name = file.getName().replaceAll("\\.{1}.+", "");
        return name;
    }

    /**
     * 将obj1具有的字段值赋值到obj2中同名字段的属性中 使用注意： 1 obj1 和 obj2中的属性要有对应的set 和 get方法 2 set 和 get
     * 方法应是默认的，即采用将字段名首字母大写形式，如setUserName 3 目前仅支持int double string 类型的字段，暂不支持其他的类型
     * 
     * @param obj1
     * @param obj2
     * @return
     */
    public static Object transferObjData(Object obj1, Object obj2) {
        try {

            Field[] fields1 = obj1.getClass().getDeclaredFields();
            Field[] fields2 = obj2.getClass().getDeclaredFields();

            for (int i = 0; i < fields1.length; i++) {
                String fieldName = fields1[i].getName();
                for (int j = 0; j < fields2.length; j++) {
                    if (fieldName != null && fieldName.equals(fields2[j].getName())) {
                        // 获取obj1类中fieldName对应的get方法
                        Method method1 = obj1.getClass().getMethod("get" + firstLetterUpper(fieldName), null);
                        Object value1 = method1.invoke(obj1, null);

                        // 如果value1的值为空，则不进行设置
                        if (value1 == null) continue;

                        Method method2 = null;
                        if (value1 != null && value1 instanceof String) {
                            method2 = obj2.getClass().getMethod("set" + firstLetterUpper(fieldName),
                                                                new Class[] { String.class });
                        } else if (value1 != null && value1 instanceof Integer) {
                            try {
                                method2 = obj2.getClass().getMethod("set" + firstLetterUpper(fieldName),
                                                                    new Class[] { Integer.class });
                            } catch (Exception e) {
                                method2 = obj2.getClass().getMethod("set" + firstLetterUpper(fieldName),
                                                                    new Class[] { int.class });
                                method2.invoke(obj2, value1);
                            }
                        } else if (value1 != null && value1 instanceof Double) {
                            try {
                                method2 = obj2.getClass().getMethod("set" + firstLetterUpper(fieldName),
                                                                    new Class[] { Double.class });
                            } catch (Exception e) {
                                method2 = obj2.getClass().getMethod("set" + firstLetterUpper(fieldName),
                                                                    new Class[] { double.class });
                                method2.invoke(obj2, value1);

                            }
                        } else if (value1 != null) {
                            return null;
                        }
                        // 获取obj2类中fieldName对应的set方法
                        // 将obj1对象中的fieldName的值置入obj2对象中
                        method2.invoke(obj2, value1);
                    }
                }
            }
            return obj2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据邮箱地址获取登录的网址
     * 
     * @param email
     * @return
     */
    public static String getLoginEmailUrl(String email) {
        if (email.trim().endsWith("@gmail.com")) {
            return "http://gmail.google.com/";
        } else if (email.trim().endsWith("@qq.com")) {
            return "http://mail.qq.com/";
        } else if (email.trim().endsWith("@163.com")) {
            return "http://mail.163.com/";
        } else if (email.trim().endsWith("@126.com")) {
            return "http://mail.126.com/";
        } else if (email.trim().endsWith("@sohu.com")) {
            return "http://mail.sohu.com/";
        } else if (email.trim().endsWith("@tom.com")) {
            return "http://mail.tom.com/";
        } else if (email.trim().endsWith("@sogou.com")) {
            return "http://mail.sogou.com/";
        } else if (email.trim().indexOf("@sina.") != -1) {
            return "http://mail.sina.com.cn/";
        } else if (email.trim().endsWith("@139.com")) {
            return "http://mail.10086.cn/";
        } else if (email.trim().indexOf("@hotmail.") != -1 || email.trim().indexOf("@msn.") != -1
                   || email.trim().indexOf("@live.") != -1) {
            // 微软系列邮箱
            return "http://www.hotmail.com/";
        } else if (email.trim().endsWith("@189.cn")) {
            return "http://www.189.cn/";
        } else if (email.trim().indexOf("@yahoo.") != -1) {
            // 雅虎邮箱
            return "http://mail.cn.yahoo.com/";
        } else if (email.trim().endsWith("@189.cn")) {
            return "http://www.189.cn/";
        } else if (email.trim().endsWith("@eyou.com")) {
            return "http://www.eyou.com/";
        } else if (email.trim().endsWith("@21cn.com")) {
            return "http://mail.21cn.com/";
        } else if (email.trim().endsWith("@188.com")) {
            return "http://www.188.com/";
        } else if (email.trim().endsWith("@yeah.net")) {
            return "http://www.yeah.net/";
        } else if (email.trim().endsWith("@foxmail.com")) {
            return "http://www.foxmail.com/";
        } else if (email.trim().endsWith("@wo.com.cn")) {
            return "http://mail.wo.com.cn/";
        } else if (email.trim().endsWith("@263.net")) {
            return "http://www.263.net/";
        } else if (email.trim().indexOf("@wulidou.") != -1) {
            return "http://mail.wulidou.com/";
        } else {
            return null;
        }
    }

    /**
     * 通过JSONArray来生成JSON串
     * 
     * @param o
     * @return
     */
    public static String getJSONAsArray(Object o) {
        try {
            JSONArray jArray = new JSONArray();
            jArray.add(o);
            return jArray.toString();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将一个对象转换为JSON格式
     * 
     * @param o
     * @return
     */
    public static String getJSONAsObj(Object o) {
        JSONObject jObj = new JSONObject();
        jObj.fromObject(o);
        return jObj.toString();
    }

    public static String getFileTypeByContentType(String ct) {
        if (ct.equals("image/pjpeg")) {
            return ".jpg";
        } else {
            return "";
        }
    }

    public static String getPercent(double f, int minDigits) {
        java.text.NumberFormat nf = java.text.NumberFormat.getPercentInstance();
        // 整数位不够的补零，小数截去部分四舍五入
        if (minDigits < 0) {
            minDigits = 1;
        }
        nf.setMaximumFractionDigits(minDigits); // 显示4位小数
        // nf.setMinimumIntegerDigits(3); // 显示6位整数
        return nf.format(f);
    }

    /**
     * 删除掉最后的一个字符
     * 
     * @param str
     * @return
     */
    public static String delLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    /**
     * 从一个字符串中抽取网址
     * 
     * @param str 含有网址的字符串
     * @return 返回一个数组。 第一个参数是开始索引、 第二个参数是结尾索引、 第三个参数是匹配的字符串
     */
    public static String[] getMatchURL(String str) {
        String regx = "[0-9a-zA-Z]+://[-_\\.a-zA-Z0-9]+(:[0-9]+)?[^ \\t<>]*";
        Pattern p = Pattern.compile(regx);
        Matcher m = p.matcher(str);
        String[] urlArray = new String[3];
        while (m.find()) {
            // m.start()
            urlArray[0] = m.start() + "";
            urlArray[1] = m.end() + "";
            urlArray[2] = m.group();
            return urlArray;
        }
        return null;
    }

    public static String htmlToText(String str) {
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("\"", "&quot;");
        str = str.replaceAll("<br/>", "\n");
        return str;
    }

    public static String textToHtml(String str) {
        str = str.replaceAll("&amp;", "&");
        str = str.replaceAll("&lt;", "<");
        str = str.replaceAll("&gt;", ">");
        str = str.replaceAll("&quot;", "\"");
        str = str.replaceAll("\n", "<br/>");
        return str;
    }

    /**
     * 判断是否为空
     * 
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        if (str == null || "".equals((StringUtil.removeBlank(str.trim())))) {
            return true;
        }
        return false;
    }

    /**
     * 把大于等于两个空格或制表符的替换为只保留一个空格
     * 
     * @param str
     * @return
     */
    public static String blankMany2One(String str) {
        return str.replaceAll(RegExpConstant.TWO_MORE_BLANK, " ");
    }

    /**
     * 移除所有空格或制表符
     * 
     * @param str
     * @return
     */
    public static String removeBlank(String str) {
        return str.replaceAll(RegExpConstant.ONE_BLANK, "");
    }

    /**
     * 判断是否存在为空的字符串
     * 
     * @param strs
     * @return
     */
    public static boolean isBlank(String... strs) {
        for (int i = 0; i < strs.length; i++) {
            if (StringUtil.isBlank(strs[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据开始符号和结束符号获取符号之间的内容 Demo: getBetweenContent("fds[0009999]fs}}]dfds", "[", "]"); OutPut:0009999]fs}}
     * 
     * @param str 原始字符串
     * @param startFlag 从开始取
     * @param endFlag 从最后开始取，多个时取最后一个
     * @return
     */
    public static String getBetweenContent(String str, String startFlag, String endFlag) {
        if (StringUtil.isBlank(str, startFlag, endFlag)) {
            return null;
        }
        if (str.indexOf(startFlag) != -1) {
            str = str.substring(str.indexOf(startFlag) + 1);
        }
        if (str.lastIndexOf(endFlag) != -1) {
            str = str.substring(0, str.lastIndexOf(endFlag));
        }
        return str;

    }

    /**
     * 获取以某个字符开始的字符串 Demo: List<String> list = new ArrayList<String>(); list.add("SELECT"); list.add("DELETE");
     * System.out.println(getStartFlagStr ("This is SQL: Select * from table ;",list)); OutPut: Select * from table ;
     * 
     * @param str
     * @param startWithList
     * @return
     */
    public static String getStartFlagStr(String str, List<String> startWithList) {
        for (int i = 0; i < startWithList.size(); i++) {
            int indexOf = str.toLowerCase().indexOf(startWithList.get(i).toLowerCase());
            if (indexOf != -1) {
                return str.substring(indexOf);
            }
        }
        return null;
    }

    /**
     * 修改首字母为大写 如：把abc_def_ghi_jkl为AbcDefGhiJkl
     * 
     * @param str
     * @param split
     * @return
     */
    public static String firstUpperCase(String str, String split) {
        if (str == null) {
            return null;
        }
        if (split != null) {
            String[] strArray = str.split(split);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < strArray.length; i++) {
                sb.append(firstUpperCase(strArray[i]));
            }
            return sb.toString();
        } else {
            return firstUpperCase(str);
        }
    }

    /**
     * 将首字母改为大写
     * 
     * @param str
     * @return
     */
    public static String firstUpperCase(String str) {
        if (str == null) {
            return null;
        }
        String firstLetter = str.substring(0, 1);
        if (isLetter(firstLetter)) {
            return firstLetter.toUpperCase() + str.substring(1);
        } else {
            return str;
        }

    }

    /**
     * 将首字母改为小写
     * 
     * @param str
     * @return
     */
    public static String firstLowerCase(String str) {
        if (str == null) {
            return null;
        }
        String firstLetter = str.substring(0, 1);
        if (isLetter(firstLetter)) {
            return firstLetter.toLowerCase() + str.substring(1);
        } else {
            return str;
        }
    }

    /**
     * 判断是否是字母
     * 
     * @param str
     * @return
     */
    public static boolean isLetter(String str) {
        if (str == null) {
            return false;
        }
        return str.matches("[a-zA-Z]+");
    }

    /**
     * 把空转为空串
     * 
     * @param str
     * @return
     */
    public static String null2Blank(String str) {
        return str == null ? "" : str;
    }

    /**
     * 把字符串转为Long
     * 
     * @param value
     * @return
     */
    public static Long longValueOf(String value) {
        if (value != null) {
            try {
                Long.valueOf(value);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 移除最后一个字符
     * 
     * @param str
     * @return
     * @throws Exception
     */
    public static String removeLastStr(String str, int remCount) {
        if (StringUtil.isBlank(str)) {
            return str;
        }
        if (str.length() < remCount) {
            throw new RuntimeException("ERROR: remCount can not more than string length");
        }
        return str.substring(0, str.length() - remCount);
    }

    /**
     * 获取某个字符之前的所有字符串 Demo:getBeforeStr("fdsfdsfds00099", "9") outPut: fdsfdsfds000
     * 
     * @param str
     * @param flag
     * @return
     */
    public static String getBeforeStr(String str, String flag) {
        if (StringUtil.isBlank(str, flag)) {
            return str;
        }
        int pos = str.indexOf(flag);
        return pos != -1 ? str.substring(0, str.indexOf(flag)) : str;
    }

    /**
     * 获取某个字符之后的所有字符串 Demo:getBeforeStr("fdsfdsfds00099", "9") outPut: fdsfdsfds000
     * 
     * @param str
     * @param flag
     * @return
     */
    public static String getAfterStr(String str, String flag) {
        if (StringUtil.isBlank(str, flag)) {
            return str;
        }
        int pos = str.indexOf(flag);
        return pos != -1 ? str.substring(pos + flag.length()) : str;
    }

    /**
     * 查找字符str中含有findStr字符串的个数
     * 
     * @param str
     * @param findStr
     * @return
     */
    public static int containCount(String str, String findStr) {
        int i = 0;
        while (str.indexOf(findStr) != -1) {
            str = str.substring(str.indexOf(findStr) + 1);
            i++;
        }
        return i;
    }

    /**
     * 移除字符串的某些前缀
     * 
     * @param str 源字符串
     * @param prefixStr 前缀，多个以逗号分隔
     * @param ignoseCase 是否忽略大小写， true -忽略大小写， false - 不忽略
     * @return
     */
    public static String removePrefix(String str, String prefixStr, boolean ignoseCase) {
        if (StringUtil.isBlank(str, prefixStr)) {
            return str;
        }

        if (ignoseCase) {
            str = str.toLowerCase();
            prefixStr = prefixStr.toLowerCase();
        }

        String[] prefixArray = prefixStr.split(",");
        for (int i = 0; i < prefixArray.length; i++) {
            if (str.startsWith(prefixArray[i])) {
                return str.substring(prefixArray[i].length());
            }
        }
        return str;
    }

    public static String defaultIfBlank(String str, String defaultStr) {
        if (isBlank(str)) {
            return defaultStr;
        } else {
            return str;
        }
    }

    /**
     * URL编码
     * 
     * @param url
     * @return
     */
    public static String urlEncode(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e);
        }
        return null;
    }

}
