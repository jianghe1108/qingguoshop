package QG;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.sf.json.JSONObject;

public class Changjing2 {
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
	
	@Test(dependsOnMethods="Loginsuccess")
	public void getAddressList() throws IOException {
		HttpGet httpGet = new HttpGet("http://study-perf.qa.netease.com/fgadmin/address/list");
		httpGet.setHeader("Content-Type","application/json");
		response = httpclient.execute(httpGet);		
		HttpEntity entity =response.getEntity();
		addressList = EntityUtils.toString(entity, "UTF-8");
		System.out.println("获得地址执行结果："+addressList);	
	}
	
	@Test(dependsOnMethods="getAddressList")
	public void getfee() throws IOException {
		JSONObject json =JSONObject.fromObject(addressList);
		JSONObject ad=json.getJSONObject("result").getJSONArray("list").getJSONObject(0);
		String addressDetail=ad.getString("province")+"_"+ad.getString("city")+"_"+ad.getString("area");

		String url=String.format("http://study-perf.qa.netease.com/common/getTransportFee?id=1&addressDetail=%s",addressDetail);
		
			HttpGet httpGet =new HttpGet(url);
			response = httpclient.execute(httpGet);
			httpGet.setHeader("Content-Type","application/json");
	    	HttpEntity entity =this.response.getEntity();
		    String feeresult=EntityUtils.toString(entity,"UTF-8");
		    System.out.println("计算运费执行结果："+feeresult);	
		    transfee =JSONObject.fromObject(feeresult).getDouble("result");  
	}

	@Test(dependsOnMethods="getfee")
    public void submit() throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost("http://study-perf.qa.netease.com/fgadmin/orders/submit");
		httpPost.setHeader("Content-Type","application/json");
		JSONObject json =JSONObject.fromObject(addressList);
		JSONObject ad=json.getJSONObject("result").getJSONArray("list").getJSONObject(0);
		String receiverName=ad.getString("receiverName");
		String cellPhone=ad.getString("cellPhone");
		String addressDetail=ad.getString("addressDetail");
		String province=ad.getString("province");
		String city=ad.getString("city");
		String area=ad.getString("area");
		double fee=this.transfee;
		JSONObject jsonPost=new JSONObject();
		jsonPost.element("skuIds", "2");
		jsonPost.element("skuNumbers", "1");
		jsonPost.element("stockIds", "74966312");
		jsonPost.element("receiverName", receiverName);
		jsonPost.element("cellPhone", cellPhone);
		jsonPost.element("addressDetail", addressDetail);
		jsonPost.element("province", province);
		jsonPost.element("city", city);
		jsonPost.element("area", area);
		jsonPost.element("voiceStatus", 0);
		jsonPost.element("needInvoice", 0);
		jsonPost.element("invoiceHead", "");
		jsonPost.element("transportFee", fee);
		jsonPost.element("logisticsCompanyId", 1);
		jsonPost.element("accessSource", "noSource");
		jsonPost.element("accessDevice", 0);
		
		StringEntity entity=new StringEntity(jsonPost.toString(),"utf-8");
		httpPost.setEntity(entity);	
		httpPost.setHeader("csrfToken","csrfToken");
		response = httpclient.execute(httpPost);	
		HttpEntity httpentity =response.getEntity();
		String submitResult=EntityUtils.toString(httpentity);
		System.out.println("提交订单执行结果："+submitResult);
		
		JSONObject jsonResult=JSONObject.fromObject(submitResult);
		assertEquals(jsonResult.getInt("code"),200);
		
		EntityUtils.consume(httpentity);
	}
}