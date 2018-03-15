package QG;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class qgsPost {
	public static void main(String[] args) throws IOException{
		String url="http://study-perf.qa.netease.com/common/fgadmin/login";
		CloseableHttpClient  httpclient =null;
		CloseableHttpResponse response = null;
		
	try {
		//1.创建httpclient对象
		httpclient = HttpClients.createDefault();

		//2.创建带请求地址的HttpPost对象
		HttpPost httpPost = new HttpPost(url);
		
		//3.设置HttpPost对象属性（指定httppost内容类型）
		httpPost.setHeader("Content-Type","application/json");
		
		//4.获得响应正文
		StringEntity entity = new StringEntity("{\"phoneArea\":86,\"phoneNumber\":\"20000000008\","+"\"password\":\"netease123\"}","utf-8");
		httpPost.setEntity(entity);

		//5.执行请求，完成登录
		response = httpclient.execute(httpPost);
		
		HttpEntity httpentity =response.getEntity();
		
		System.out.println("执行结果是："+EntityUtils.toString(httpentity));

		//6.释放资源
		EntityUtils.consume(httpentity);
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		
		//6.断开连接
		response.close();
		httpclient.close();
	}
	}
}