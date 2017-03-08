package common.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

public class MyByteBufUtil {
	private static final Logger LOG = Logger.getLogger(MyByteBufUtil.class);
	
	public static void printHex(byte[] bytes){
		for (byte b:bytes){
			System.out.format("%x", b); 
		}
	}
	
	public static void printHex1(byte[] bytes){
		System.out.println( Hex.encodeHexString(bytes) );
	}
	
	public static void main(String[] args) {
		printHex("史纪军".getBytes()); 
		System.out.println(); 
		printHex1("史纪军".getBytes()); 
	}
	
	public static void printDebugInfo(ByteBuf bf){
		LOG.debug("-----------------------------------");
		LOG.debug("The identityHashCode:" + System.identityHashCode(bf));
		LOG.debug("The className of msg:" + bf.getClass().getCanonicalName());
		LOG.debug("The hexDump of msg:" + ByteBufUtil.hexDump(bf));
		LOG.debug("The referenceCount of msg:" + bf.refCnt());
		LOG.debug("-----------------------------------");
	}
	
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
}
