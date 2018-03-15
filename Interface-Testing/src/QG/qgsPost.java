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
		//1.����httpclient����
		httpclient = HttpClients.createDefault();

		//2.�����������ַ��HttpPost����
		HttpPost httpPost = new HttpPost(url);
		
		//3.����HttpPost�������ԣ�ָ��httppost�������ͣ�
		httpPost.setHeader("Content-Type","application/json");
		
		//4.�����Ӧ����
		StringEntity entity = new StringEntity("{\"phoneArea\":86,\"phoneNumber\":\"20000000008\","+"\"password\":\"netease123\"}","utf-8");
		httpPost.setEntity(entity);

		//5.ִ��������ɵ�¼
		response = httpclient.execute(httpPost);
		
		HttpEntity httpentity =response.getEntity();
		
		System.out.println("ִ�н���ǣ�"+EntityUtils.toString(httpentity));

		//6.�ͷ���Դ
		EntityUtils.consume(httpentity);
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		
		//6.�Ͽ�����
		response.close();
		httpclient.close();
	}
	}
}