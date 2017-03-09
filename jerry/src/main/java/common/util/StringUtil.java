package common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    /**
     * 把中文字符串转换为十六进制Unicode编码字符串
     * 
     * @param s
     *            中文字符串
     * @return
     */
    public static String stringToUnicode(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            if (ch > 255)
                str += "\\u" + Integer.toHexString(ch);
            else
                str += "\\" + Integer.toHexString(ch);
        }
        return str;
    }
 
    /**
     * 把十六进制Unicode编码字符串转换为中文字符串, 将\u848B\u4ECB\u77F3转化成蒋介石，注意格式
     * 
     * @param str
     *            eg:\u848B\u4ECB\u77F3
     * @return 蒋介石
     */
    public static String unicodeToString(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }
    
    public static boolean isEmpty(String str) {
        if (str == null || str.trim().length() <= 0) {
            return true;
        }
        return false;
    }
}
