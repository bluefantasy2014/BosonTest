package BosonTest.jerry;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import common.util.MyByteBufUtil;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

public class NERTest {
	private static final Logger LOG = Logger.getLogger(NERTest.class);
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		  PropertyConfigurator.configure("log4j.properties");
		  byte[] b_utf8 = "成".getBytes("UTF-8"); 
		
		  String s_gbk = new String(b_utf8,"UTF-8");
		  System.out.println(s_gbk); 
		  LOG.info("--------------------");
		  LOG.info(ByteBufUtil.prettyHexDump(Unpooled.wrappedBuffer(b_utf8)));
		  LOG.info("--------------------");
		  
		  MyByteBufUtil.printHex("成".getBytes("UTF-8"));
		  
		  LOG.info(MyByteBufUtil.stringToUnicode("成"));
		  LOG.info(MyByteBufUtil.stringToUnicode("abc"));
	}
}
