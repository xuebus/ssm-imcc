package com.zhaopin.core.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class CommonUtil {

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() <= 0;
    }
    public static boolean isNullOrZero(Integer param) {
        return param == null ||param.intValue()==0;
    }
    public static boolean isNullOrZero(Long param) {
        return param == null ||param.intValue()==0;
    }

    /**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2位
     * @param value 指定的字符串
     * @return 字符串的长度
     */
    public static int stringLength(String value) {
        if (CommonUtil.isNullOrEmpty(value)) {
            return 0;
        }
        int valueLength = 0;
        //textarea 不同浏览器换行不同， 统一转为\n
        value = value.replace("\r\n", "\n");
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }

    /**
     * 获取以某分隔符分开后的项的个数
     *
     * @param value     指定的字符串
     * @param splitChar 分隔符
     * @return list
     */
    public static List<Long> getSplitLongList(String value, String splitChar) {
        List<Long> valueList = new ArrayList<Long>();
        if (!CommonUtil.isNullOrEmpty(value)) {
            String[] values = value.split(splitChar);
            for (int i = 0; i < values.length; i++) {
                if (values[i] != "") {
                    valueList.add(CommonUtil.getLong(values[i]));
                }
            }
        }
        return valueList;
    }

    /**
     * 去除字符窜尾字符
     *
     * @param str     字符串
     * @param charStr 尾字符
     * @return string
     */
    public static String trimEnd(String str, String charStr) {
        if (!CommonUtil.isNullOrEmpty((str)) && !CommonUtil.isNullOrEmpty(charStr)) {
            if (str.substring(str.length() - 1).equals(charStr)) {
                return str.substring(0, str.length() - 1);
            }
        }
        return str;
    }

    /**
     * 去除字符窜开始的字符字符串
     *
     * @param str     字符串
     * @param charStr 开始的字符串
     * @return string
     */
    public static String trimStart(String str, String charStr) {
        if (!CommonUtil.isNullOrEmpty((str)) && !CommonUtil.isNullOrEmpty(charStr)) {
            if(str.startsWith(charStr)){
                return str.substring(str.indexOf(charStr)+1);
            }
        }
        return str;
    }

    /**
     * 获取更加详细的异常信息
     *
     * @param ex
     * @return
     */
    public static String getExceptionAllinformation(Throwable ex) {
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw, true));
        return sw.toString();
    }

    /**
     * 获取当前时间，并格式化输出
     *
     * @param format 格式化例如 "yyyy-MM-dd HH:mm:ss"
     * @return 返回格式化时间的字符串
     */
    public static String getDateNowByFormat(String format) {
        if (format == null || format.length() == 0) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(Calendar.getInstance().getTime());
    }

    /**
     * 将时间转化为字符串
     *
     * @param date
     * @return
     */
    public static String dataParseString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 获取当前ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        //Proxy-Client-IP 字段和 WL-Proxy-Client-IP 字段只在 Apache（Weblogic Plug-In Enable）+WebLogic 搭配下出现
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

    /**
     * 返回一个随机的英文字母字符串
     *
     * @param len
     * @return
     */
    public static String makeCodeString(int len) {
        if (len < 1) {
            return "";
        }
        Integer number;
        String checkcode = "";
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            number = random.nextInt(100000);
            if (number % 2 == 0) {
                checkcode += (char) ('a' + (char) (int) (number % 26));
            } else {
                checkcode += (char) ('A' + (char) (int) (number % 26));
            }
        }
        return checkcode;
    }

    /**
     * 获取以某分隔符分开后的项的个数
     *
     * @param value     指定的字符串
     * @param splitChar 分隔符
     * @return list
     */
    public static List<String> getSplitList(String value, String splitChar) {
        List<String> valueList = new ArrayList<String>();
        if (!CommonUtil.isNullOrEmpty(value)) {
            String[] values = value.split(splitChar);
            for (int i = 0; i < values.length; i++) {
                if (values[i] != "") {
                    valueList.add(values[i]);
                }
            }
        }
        return valueList;
    }

    /**
     * 判断请求是否来自于移动端，手机和pad。
     *
     * @param userAgent
     * @return
     */
    public static boolean isFromMoblie(String userAgent) {
        boolean bRes = false;
        if (!isNullOrEmpty(userAgent)) {
            userAgent = userAgent.toLowerCase();
            if (userAgent.indexOf("mobile") != -1 ||
                    userAgent.indexOf("mobi") != -1 ||
                    userAgent.indexOf("android") != -1 ||
                    userAgent.indexOf("iphone") != -1 ||
                    userAgent.indexOf("nokia") != -1 ||
                    userAgent.indexOf("samsung") != -1 ||
                    userAgent.indexOf("sonyericsson") != -1 ||
                    userAgent.indexOf("mot") != -1 ||
                    userAgent.indexOf("blackberry") != -1 ||
                    userAgent.indexOf("lg") != -1 ||
                    userAgent.indexOf("htc") != -1 ||
                    userAgent.indexOf("j2me") != -1 ||
                    userAgent.indexOf("ucweb") != -1 ||
                    userAgent.indexOf("opera mini") != -1) {
                bRes = true;
            }
        }
        return bRes;
    }

    public static String padLeft(String oriStr, int len, char alexin) {
        String str = "";
        int strlen = oriStr.length();
        if (strlen < len) {
            for (int i = 0; i < len - strlen; i++) {
                str = str + alexin;
            }
        }
        str = str + oriStr;
        return str;
    }

    public static String padRight(String oriStr, int len, char alexin) {
        String str = "";
        int strlen = oriStr.length();
        if (strlen < len) {
            for (int i = 0; i < len - strlen; i++) {
                str = str + alexin;
            }
        }
        str = oriStr + str;
        return str;
    }

    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

    /**
     * 删除字符串中的html标记
     *
     * @param htmlStr
     * @return
     */
    public static String clearHtml(String htmlStr) {
        if (!CommonUtil.isNullOrEmpty(htmlStr)) {
            Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            Matcher m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签

            Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            Matcher m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签

            Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            Matcher m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签

            return htmlStr; // 返回文本字符串
        }
        return htmlStr;
    }

    /**
     * 字符串编码转换的实现方法
     *
     * @param str        待转换编码的字符串
     * @param newCharset 目标编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String changeCharset(String str, String newCharset)
            throws UnsupportedEncodingException {
        if (str != null) {
            //用默认字符编码解码字符串。
            byte[] bs = str.getBytes();
            //用新的字符编码生成字符串
            return new String(bs, newCharset);
        }
        return null;
    }

    /**
     * 将字符编码转换成UTF-8码
     */
    public static String toUTF_8(String str) {
        try {
            return CommonUtil.changeCharset(str, "UTF_8");
        } catch (UnsupportedEncodingException ex) {
        }
        return str;
    }

    /**
     * 将字符编码转换成gb2312码
     */
    public static String toGb2312(String str) {
        try {
            return CommonUtil.changeCharset(str, "gb2312");
        } catch (UnsupportedEncodingException ex) {
        }
        return str;
    }

    /**
     * 去除特殊字符
     *
     * @param htmlStr
     * @return
     */
    public static String clearSpecialChar(String htmlStr, String regEx) {
        if (!CommonUtil.isNullOrEmpty(htmlStr) && !CommonUtil.isNullOrEmpty(regEx)) {
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(htmlStr);
            return m.replaceAll("");
        }
        return htmlStr;
    }

    /**
     * 过滤sql等非法关键字
     *
     * @param str
     * @return
     */
    public static String getSafeQuery(String str) {
        if (!CommonUtil.isNullOrEmpty(str)) {
            str = str.replace(">", "").replace("<", "");
            str = str.replaceAll("(?i)insert", "");
            str = str.replaceAll("(?i)update", "");
            str = str.replaceAll("(?i)delete", "");
            str = str.replaceAll("(?i)select", "");
            //str=str.replaceAll("(?i)and", "");
            str = str.replaceAll("(?i)where", "");
            //str=str.replaceAll("(?i)or", "");
            //str=str.replaceAll("(?i)limit", "");
        }
        return str;
    }
    //region  类型转换


    public static int getInt(Object obj) {
        int def = 0;
        if (obj != null) {
            try {
                def = Integer.parseInt(obj == null ? "" : obj.toString());
            } catch (Exception ex) {
            }

        }
        return def;
    }

    public static int getInt(Object obj, int def) {
        if (obj != null) {
            try {
                def = Integer.parseInt(obj == null ? "" : obj.toString());
            } catch (Exception ex) {
            }

        }
        return def;
    }

    public static long getLong(Object obj, long def) {
        if (obj != null) {
            try {
                def = Long.parseLong(obj == null ? "" : obj.toString());
            } catch (Exception ex) {
            }

        }
        return def;
    }

    public static long getLong(Object obj) {
        long def = 0;
        if (obj != null) {
            try {
                def = Long.parseLong(obj == null ? "" : obj.toString());
            } catch (Exception ex) {
            }

        }
        return def;
    }

    public static double getDouble(Object obj, double def) {
        if (obj != null) {
            try {
                def = Double.parseDouble(obj == null ? "" : obj.toString());
            } catch (Exception ex) {
            }

        }
        return def;
    }

    public static double getDouble(Object obj) {
        double def = 0.0;
        if (obj != null) {
            try {
                def = Double.parseDouble(obj == null ? "" : obj.toString());
            } catch (Exception ex) {
            }

        }
        return def;
    }

    public static String getString(Object obj) {
        String def = "";
        if (obj != null) {
            def = obj.toString();

        }
        return def;

    }

    public static String getString(Object obj, String def) {
        if (obj != null) {
            def = obj.toString();

        }
        return def;

    }
    //endregion

    //过滤特殊字符[]
    public static String StringFilter(String str) throws PatternSyntaxException {
        String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * hashmap转url拼接地址
     *
     * @param url  接口地址
     * @param data 字符串
     * @return string
     */
    public static String QueryString(String url, Map<String, Object> data) {
        if (data != null && data.size() > 0) {
            for (Map.Entry<String, Object> item : data.entrySet()) {
                url += item.getKey() + "=" + item.getValue() + "&";
            }
            url = trimEnd(url, "&");
        }
        return url;
    }

    /**
     * 将特殊字符转义
     *
     * @return
     */
    public static String htmlEiscode(String str) {
        if (!isNullOrEmpty(str)) {
            str = str.replace(">", "&gt;");
            str = str.replace("<", "&lt;");
            str = str.replace(" ", "&nbsp;");
            str = str.replace("\\", "&quot;");
            str = str.replace("|", "&brvbar;");
            str = str.replace("'", "\\'");
            str = str.replace("\"", "\\");
        }
        return str;
    }

    /**
     * 将特殊字符转义
     *
     * @return
     */
    public static String htmlencode(String str) {
        if (!isNullOrEmpty(str)) {
            str = str.replace(">", "&gt;");
            str = str.replace("<", "&lt;");
            str = str.replace(" ", "&nbsp;");
        }
        return str;
    }

    /**
     * 将特殊字符转义 反
     *
     * @returns
     */
    public static String htmldecode(String str) {
        if (!isNullOrEmpty(str)) {
            str = str.replace("&gt;", ">");
            str = str.replace("&lt;", "<");
            str = str.replace("&nbsp;", " ");
        }
        return str;
    }

    /**
     * 去除字符
     *
     * @param str     字符串
     * @param charStr 尾字符
     * @return string
     */
    public static String replaceChar(String str, String charStr) {
        String result = "";
        if (!CommonUtil.isNullOrEmpty((str)) && !CommonUtil.isNullOrEmpty(charStr)) {
            for (String item : str.split(charStr)) {
                if (!item.equals("")) {
                    result += item + ",";
                }
            }
            result = CommonUtil.trimEnd(result, charStr);
        }
        return result;
    }

    /**
     * 获取uuid
     *
     * @return
     */
    public static String getTaskId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String urlEncode(String value)  {
        try {
            return URLEncoder.encode(value, "UTF-8");
        }catch (Exception ex){}
        return value;
    }

    public static String urlDncode(String value) {
        try {
            return URLDecoder.decode(value, "UTF-8");
        }catch (Exception ex){}
        return value;
    }


    public static void main(String[] args) {
//        boolean c= a.startsWith("f");
//        long startTime=System.currentTimeMillis();   //获取开始时间
////        for(int j = 0; j< 100; j++){
////            System.out.println((int)((Math.random()*9+1)*100000));
////        }
//        long endTime=System.currentTimeMillis(); //获取结束时间
//        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");

    }

    /**
     * 将ISO88591编码的字符串转成utf8。
     *
     * @param str
     * @return
     */
    public static String encodeISO88591ToUtf8(String str) {
        if (CommonUtil.isNullOrEmpty(str)) {
            return "";
        }

        try {
            return new String(str.getBytes("ISO-8859-1"), "utf-8");
        } catch (Exception e) {
            return "";
        }
    }
}
