package QGkuangjia;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.testng.annotations.Test;
import com.apitest.core.HttpUtils;

import net.sf.json.JSONObject;

public class Addressnew {
	public void addressnew(Object receiverName,Object cellPhone,Object addressDetail,Object province,Object city,Object area,int code1) throws ClientProtocolException, IOException {
		
		CookieStore cookie=Commen.getCookie();

		JSONObject json=new JSONObject();
		json.element("receiverName", receiverName);
		json.element("cellPhone", cellPhone);
		json.element("addressDetail", addressDetail);
		json.element("province", province);
		json.element("city", city);
		json.element("area", area);
		String result=HttpUtils.post("/fgadmin/address/new",json,cookie);
		System.out.println(result);
		
     	JSONObject json1 = JSONObject.fromObject(result);
     	System.out.println(json1.getString("code"));
		assertEquals(json1.getInt("code"),code1);
	}
	@Test
	public void addnew1() throws ClientProtocolException, IOException {
		addressnew("jiang","12345678901","浙江大学","浙江省","杭州市","滨江区",200);
	}	
	@Test
	public void addressnew2() throws ClientProtocolException, IOException {
		addressnew(123,"12345678901","浙江大学","浙江省","杭州市","滨江区",400);
	}	
}
