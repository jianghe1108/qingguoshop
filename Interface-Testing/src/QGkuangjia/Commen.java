package QGkuangjia;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Commen {
	     public static CookieStore getCookie() throws ClientProtocolException, IOException {
	    	 //����ȫ������
	    	 RequestConfig gloableConfig=RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
	    	 //����cookieSstoreʵ��
	    	 CookieStore cookiestore=new BasicCookieStore();
	    	 //����HTTPClient������
	    	 HttpClientContext context=HttpClientContext.create();
	    	 //����client����
	    	 CloseableHttpClient  httpclient=HttpClients.custom().setDefaultRequestConfig(gloableConfig).setDefaultCookieStore(cookiestore).build();
	    	 
	    	 
	    	 HttpPost httpPost = new HttpPost("http://study-perf.qa.netease.com/common/fgadmin/login");
				httpPost.setHeader("Content-Type","application/json");
			
				StringEntity entity = new StringEntity("{\"phoneArea\":86,\"phoneNumber\":\"2000000000118\","+"\"password\":\"netease123\"}","utf-8");
				httpPost.setEntity(entity);
				CloseableHttpResponse response=httpclient.execute(httpPost,context);
//				for (Cookie c:cookiestore.getCookies()) {
//					System.out.println(c.getName()+c.getValue());
//				}
				return cookiestore;
	     }
	}