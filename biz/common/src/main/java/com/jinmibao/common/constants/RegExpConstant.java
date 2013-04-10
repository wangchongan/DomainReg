package com.jinmibao.common.constants;

public class RegExpConstant {

    /* 判断是浮点型数值 */
    public final static String FLOAT             = "^(-?\\d+)(\\.\\d+)?$";

    /* 判断大于两个空格或制表符的字符 */
    public final static String TWO_MORE_BLANK    = "[ \\t]{2,}";

    /* 判断一个空格或制表符的字符 */
    public final static String ONE_BLANK         = "[ \\t]";

    /* 判断为合法的用户数字ID */
    public final static String USER_ID           = "^([1-9]){1}([0-9]){" + (InputCheckConstant.USER_ID_MIN - 1) + ","
                                                   + (InputCheckConstant.USER_ID_MAX - 1) + "}$";

    /* 判断为合法注册邮箱 */
    public final static String EMAIL             = "^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\\\u00A0-\\\\uD7FF\\\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))$";

    /* 判断为用户昵称-合法的字符 */
    public final static String USER_NICK_LETTERS = "^([\u4e00-\u9fa5]|[A-Za-z]){1}([\u4e00-\u9fa5]|[A-Za-z]|[0-9]|\\_)*([\u4e00-\u9fa5]|[A-Za-z]|[0-9]){1}$";

    /* 判断中文字符 */
    public final static String CHINESE_LETTERS   = "[\u4e00-\u9fa5]";

    public static void main(String[] args) {
        String testStr = "100000";
        String testStrEmail = "1@1.com";
        String testStrUserNick = "_f__sd发生的_fsd发生的";
        System.out.println(testStrEmail.matches(EMAIL));
        System.out.println(testStr.matches(USER_ID));
        System.out.println(testStrUserNick.matches(USER_NICK_LETTERS));
        System.out.println(testStrUserNick.replaceAll(CHINESE_LETTERS, "aa"));
        System.out.println(testStrUserNick.indexOf("__"));
    }

}
