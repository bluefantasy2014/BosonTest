package common.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;




public class HttpClientUtil {
	private static final Logger LOG = Logger.getLogger(HttpClientUtil.class);
	  private static final int TIMEOUT = 10000;
	
	public static String get(String url, int timeOut) {
		LOG.info("Get url: " + url);
		Request request = Request.Get(url);
		if (timeOut > 0) {
			request.connectTimeout(timeOut).socketTimeout(timeOut);
		}
		try {
			HttpResponse returnResponse = request.execute().returnResponse();
			int statusCode = returnResponse.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				String result = StringUtil.unicodeToString(EntityUtils.toString(returnResponse.getEntity(), StandardCharsets.UTF_8));
				LOG.info("Get return: " + result);
				return result;
			} else {
				LOG.error("ExecuteMethod failed: " + statusCode);
			}
		} catch (Exception e) {
			LOG.error("url:" + url + ", " + e.getMessage(), e);
		}
		return null;
	}
	
	
	public static String post(String url, String data){
		return post(url, data, TIMEOUT);
	}
	
	 
	public static String post(String url, String data, int timeOut) {
		return postWithHeader(url, data, timeOut);
	}
	
	public static String postWithHeader(String url, String data, int timeOut, Header... headers) {
		LOG.info("Post url: " + url);
		
		if (StringUtil.isEmpty(url) || StringUtil.isEmpty(data)) {
			return null;
		}
		
		Request request = Request.Post(url).bodyString(data, ContentType.APPLICATION_JSON).addHeader(new BasicHeader("Accept", ContentType.APPLICATION_JSON.toString()));
		if (timeOut > 0) {
			request.connectTimeout(timeOut).socketTimeout(timeOut);
		}
		if(headers!=null&headers.length>0){
			for (Header header : headers) {
				request.addHeader(header);
			}
		}
		try {
			HttpResponse returnResponse = request.execute().returnResponse();
			int statusCode = returnResponse.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				LOG.info("post successfully.");
				String result = StringUtil.unicodeToString(EntityUtils.toString(returnResponse.getEntity(), StandardCharsets.UTF_8));
				LOG.info("post return: " + result);
				return result;
			} else {
				LOG.error("post failed: " + statusCode);
			}
		} catch (Exception e) {
			LOG.error("url:" + url + ", " + e.getMessage(), e);
		}
		return null;
	}
	
}
