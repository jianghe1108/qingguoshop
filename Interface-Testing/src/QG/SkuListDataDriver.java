package QG;

import static org.testng.Assert.assertEquals;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import net.sf.json.JSONObject;

public class SkuListDataDriver {
	CloseableHttpClient  httpclient =null;
	CloseableHttpResponse response = null;
	
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
	
	@DataProvider(name="goodsId")
	public Object[][] skulist(){
		return new Object[][]{
			{"",200,"success"},
			{"goodsId=1",200,"success"},
			{"goodsId=2147483648",400,"商品ID不正确"},
			{"goodsId=9999",201,"商品ID不存在"},
			{"goodsId=\"1\"",200,"商品ID参数类型不正确"},
		};
    }
	
	@Test(dataProvider="goodsId")
	public void testSKUList(String Id,int code,String message) throws IOException {
		HttpGet httpGet = new HttpGet("http://study-perf.qa.netease.com/common/skuList"+"?"+Id);
		httpGet.setHeader("Content-Type","application/json");
		response = httpclient.execute(httpGet);
		HttpEntity entity =response.getEntity();
		String result = EntityUtils.toString(entity, "UTF-8");	
	
		JSONObject json = JSONObject.fromObject(result);
		assertEquals(json.getString("message"),message);
		assertEquals(json.getInt("code"),code);
//		JSONArray jsonArray = json.getJSONArray("result");
//		JSONObject fobject =jsonArray.getJSONObject(0);
//		assertEquals(json.getInt("price"),199);
		
		EntityUtils.consume(entity);
	}
	

}