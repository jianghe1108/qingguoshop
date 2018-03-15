package QG;

import static org.testng.Assert.assertEquals;

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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import net.sf.json.JSONObject;

public class LoginTest {
	CloseableHttpClient  httpclient =null;
	CloseableHttpResponse response = null;
	
	@BeforeClass
	public void initHttpClient() {
		httpclient = HttpClients.createDefault();
	}
	
	@DataProvider(name="login")
	public Object[][] loginUser(){
		return new Object[][]{
			{"{\"phoneArea\":\"86\",\"phoneNumber\":\"20000000003\",\"password\":\"netease123\"} ",200,"success"},
			{"{\"phoneArea\":86,\"phoneNumber\":\"20000000003\","+"\"password\":\"netease123\"}",400,"用户名或者密码错误"},
			{"{\"phoneArea\":\"86\",\"phoneNumber\":\"2000000000\",\"password\":\"netease123\"}",401,"用户名或者密码错误"}
		};
    }
	
	@Test(dataProvider="login")
	public void testLogin(String userInfo,int code,String message) throws IOException {
		HttpPost httpPost = new HttpPost("http://study-perf.qa.netease.com/common/fgadmin/login");
		httpPost.setHeader("Content-Type","application/json");
		
		StringEntity entity = new StringEntity(userInfo,"utf-8");
		httpPost.setEntity(entity);		
		
		response = httpclient.execute(httpPost);			
		HttpEntity httpentity =response.getEntity();
		
		String result = EntityUtils.toString(entity, "UTF-8");	
		System.out.println(result);
		
		JSONObject json = JSONObject.fromObject(result);
		assertEquals(json.getString("message"),message);
		assertEquals(json.getInt("code"),code);
			
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