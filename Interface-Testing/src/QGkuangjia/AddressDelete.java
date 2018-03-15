package QGkuangjia;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.testng.annotations.Test;
import com.apitest.core.HttpUtils;
import net.sf.json.JSONObject;

public class AddressDelete{
	public void addressDelete(int id,int code) throws ClientProtocolException, IOException {
		CookieStore cookie=Commen.getCookie();
		
		JSONObject json=new JSONObject();
		json.element("id",id);
		String result=HttpUtils.post("/fgadmin/address/delete", json,cookie);
		JSONObject json1 = JSONObject.fromObject(result);
		assertEquals(json1.getInt("code"),code);
	}
	
	@Test
	public void Delete1() throws ClientProtocolException, IOException {
		addressDelete(1,200);
	}
	
	@Test
	public void Delete2() throws ClientProtocolException, IOException {
		addressDelete(9999,400);
	}
}