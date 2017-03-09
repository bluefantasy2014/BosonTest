package BosonTest.jerry;

import org.apache.http.message.BasicHeader;
import org.apache.log4j.PropertyConfigurator;

import common.util.HttpClientUtil;
import common.util.StringUtil;

public class HttpClientUtilTest {
	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
//		String url = "http://192.168.0.100:8081"; 
		String url = "http://api.bosonnlp.com/depparser/analysis"; 
//		String data = "我以最快的速度吃了午饭"; 
		String data = "请您尽快缴费，以避免逾期"; 
		data = StringUtil.stringToUnicode(data); 
		data = "[\"" + data + "\"]";
		String ret = HttpClientUtil.postWithHeader(url, data, 20000, new BasicHeader("X-Token","0AR1tJmW.13471.t2Oogi0_LPIL"));
		System.out.println(ret);
	}
}
