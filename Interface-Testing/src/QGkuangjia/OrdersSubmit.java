package QGkuangjia;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.testng.annotations.Test;
import com.apitest.core.HttpUtils;
import net.sf.json.JSONObject;

public class OrdersSubmit{
	public void submit(Object skuIds,Object skuNumbers,Object stockIds,Object receiverName,Object cellPhone,Object addressDetail,Object province,Object city,Object area,Object voiceStatus,Object needInvoice,Object invoiceHead,Object transportFee,Object logisticsCompanyId,Object accessSource,Object accessDevice,int code) throws ClientProtocolException, IOException {
		CookieStore cookie=Commen.getCookie();
		
		JSONObject json=new JSONObject();
		json.element("skuIds", skuIds);
		json.element("skuNumbers", skuNumbers);
		json.element("stockIds", stockIds);
		json.element("receiverName", receiverName);
		json.element("cellPhone", cellPhone);
		json.element("addressDetail", addressDetail);
		json.element("province", province);
		json.element("city", city);
		json.element("area", area);
		json.element("voiceStatus", voiceStatus);
		json.element("needInvoice", needInvoice);
		json.element("invoiceHead", invoiceHead);
		json.element("transportFee", transportFee);
		json.element("logisticsCompanyId", logisticsCompanyId);
		json.element("accessSource", accessSource);
		json.element("accessDevice", accessDevice);
		String result=HttpUtils.post("/fgadmin/orders/submit",json,cookie);
		System.out.println(result);
		JSONObject json1 = JSONObject.fromObject(result);
		assertEquals(json1.getInt("code"),code);
	}
	@Test
	public void ordersubmit1() throws ClientProtocolException, IOException {
		submit("2","1","74966313","张三","12615813537","1 栋 3 单元","浙江省","杭州市","滨江区","0","0","","0","1","noSource","0",200);
	}
	
	@Test
	public void ordersubmit2() throws ClientProtocolException, IOException {
		submit("","1","74966313","张三","12615813537","1 栋 3 单元","浙江省","杭州市","滨江区","0","0","","0","1","noSource","0",400);
	}
}
