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
		//1.��ʼ��httpclient����
		httpclient = HttpClients.createDefault();

		//2.��ʼ��HttpGet����
		HttpGet httpGet = new HttpGet(url);
		
		//3.����Header
		httpGet.setHeader("Content-Type","application/json");
		
		//4.ִ�����󣬻����Ӧ
		response = httpclient.execute(httpGet);

		//5.�����Ӧʵ��
		HttpEntity entity = response.getEntity();
		
		//6.�����Ӧ����
		String result = EntityUtils.toString(entity, "UTF-8");
		System.out.println(result);

		//7.�ͷ���Դ
		EntityUtils.consume(entity);
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		
		//8.�Ͽ�����
		response.close();
		httpclient.close();
	}
	}
}