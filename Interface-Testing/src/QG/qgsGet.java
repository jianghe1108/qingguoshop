package QG;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class qgsGet {
	public static void main(String[] args) throws IOException{
		String url="http://study-perf.qa.netease.com/common/skuList";
		CloseableHttpClient  httpclient =null;
		CloseableHttpResponse response = null;

	try {
		//1.初始化httpclient对象
		httpclient = HttpClients.createDefault();

		//2.初始化HttpGet请求
		HttpGet httpGet = new HttpGet(url);
		
		//3.设置Header
		httpGet.setHeader("Content-Type","application/json");
		
		//4.执行请求，获得响应
		response = httpclient.execute(httpGet);

		//5.获得响应实体
		HttpEntity entity = response.getEntity();
		
		//6.获得响应正文
		String result = EntityUtils.toString(entity, "UTF-8");
		System.out.println(result);

		//7.释放资源
		EntityUtils.consume(entity);
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		
		//8.断开连接
		response.close();
		httpclient.close();
	}
	}
}