package QG;

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

public class qingguoshop {
	CloseableHttpClient  httpclient =null;
	CloseableHttpResponse response = null;
	
	@BeforeClass
	public void initHttpClient() {
		httpclient = HttpClients.createDefault();
	}
	
	@Test
	public void testLogin() throws IOException {
		HttpPost httpPost = new HttpPost("http://study-perf.qa.netease.com/common/fgadmin/login");
		httpPost.setHeader("Content-Type","application/json");
		
		StringEntity entity = new StringEntity("{\"phoneArea\":\"86\",\"phoneNumber\":\"20000000003\",\"password\":\"netease123\"}","utf-8");
		httpPost.setEntity(entity);

		response = httpclient.execute(httpPost);
			
		HttpEntity httpentity =response.getEntity();
			
		System.out.println("1.执行结果是："+EntityUtils.toString(httpentity));
			
		EntityUtils.consume(httpentity);	
	}
	
	@Test(dependsOnMethods="testLogin")
	public void testAddaddress() throws IOException {
		HttpPost httpPost = new HttpPost("http://study-perf.qa.netease.com/fgadmin/address/new");
		httpPost.setHeader("Content-Type","application/json");
		
		StringEntity entity = new StringEntity("{\"id\":\"\",\"receiverName\":\"qq\",\"cellPhone\":\"13677889900\",\"province\":\"浙江省\",\"city\":\"杭州市\",\"area\":\"滨江区\",\"addressDetail\"\r\n" + 
				":\"1324\"}","utf-8");
		httpPost.setEntity(entity);
		
		response = httpclient.execute(httpPost);
			
		HttpEntity httpentity =response.getEntity();
			
		System.out.println("2.执行结果是："+EntityUtils.toString(httpentity));
			
		EntityUtils.consume(httpentity);	
	}
	
	@Test(dependsOnMethods="testAddaddress")
	public void testOrdersubmit() throws IOException {
		HttpPost httpPost = new HttpPost("http://study-perf.qa.netease.com/fgadmin/orders/submit");
		httpPost.setHeader("Content-Type","application/json");
		
		StringEntity entity = new StringEntity("id\r\n" + 
				"	\r\n" + 
				"	74966314\r\n" + 
				"searchParamList\r\n" + 
				"	\r\n" + 
				"	[]\r\n" + 
				"phoneArea\r\n" + 
				"	\r\n" + 
				"	\"86\"\r\n" + 
				"phoneNumber\r\n" + 
				"	\r\n" + 
				"	\"20000000000\"\r\n" + 
				"createTime\r\n" + 
				"	\r\n" + 
				"	1454057846653\r\n" + 
				"userAccount\r\n" + 
				"	\r\n" + 
				"	\"86/20000000000\"\r\n" + 
				"userName\r\n" + 
				"	\r\n" + 
				"	\"测试用户20\"\r\n" + 
				"platform\r\n" + 
				"	\r\n" + 
				"	0\r\n" + 
				"next1\r\n" + 
				"	\r\n" + 
				"	0\r\n" + 
				"next2\r\n" + 
				"	\r\n" + 
				"	0\r\n" + 
				"createTimeStr\r\n" + 
				"	\r\n" + 
				"	\"2016-01-29 16:57:26\"\r\n" + 
				"platformDescribe\r\n" + 
				"	\r\n" + 
				"	\"手机\"","utf-8");
		httpPost.setEntity(entity);
		
		response = httpclient.execute(httpPost);
			
		HttpEntity httpentity =response.getEntity();
			
		System.out.println("3.执行结果是："+EntityUtils.toString(httpentity));
			
		EntityUtils.consume(httpentity);	
	}
	
	@AfterClass
	public void afterClose() throws IOException {
		if(response!=null)
		response.close();
		if(httpclient!=null)
		httpclient.close();
	}
}