package com.edu.test1;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HttpUtil {
	CloseableHttpClient  httpclient =null;
	CloseableHttpResponse response = null;
	String addressList =null;
	double transfee; 
	
	@BeforeClass
	public void initHttpClient() {
		httpclient = HttpClients.createDefault();
	}
	
	@AfterClass
	public void afterClose() throws IOException {
		if(response!=null)
		response.close();
		if(httpclient!=null)
		httpclient.close();
	}
	@Test
	public void Loginsuccess() throws IOException {
		HttpPost httpPost = new HttpPost("http://study-perf.qa.netease.com/common/fgadmin/login");
		httpPost.setHeader("Content-Type","application/json");
		StringEntity entity = new StringEntity("{\"phoneArea\":\"86\",\"phoneNumber\":\"2000000000118\",\"password\":\"netease123\"}","utf-8");
		httpPost.setEntity(entity);
		response = httpclient.execute(httpPost);	
		HttpEntity httpentity =response.getEntity();
		System.out.println("账户登录执行结果是："+EntityUtils.toString(httpentity));
		EntityUtils.consume(httpentity);	
	}
}