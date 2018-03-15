package QGkuangjia;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;
import com.apitest.core.HttpUtils;

import net.sf.json.JSONObject;

public class SkuLIstTest{
	public void skuList(String goodsId,int code) {
		JSONObject json=new JSONObject();
		json.element("phoneArea",goodsId);
		String result=HttpUtils.get("/common/skuList");
		System.out.println(result);
		JSONObject json1 = JSONObject.fromObject(result);
		assertEquals(json1.getInt("code"),code);
	}
	
	public void skuList1(long goodsId,int code) {
		JSONObject json=new JSONObject();
		json.element("phoneArea",goodsId);
		String result=HttpUtils.get("/common/skuList");
		System.out.println(result);
		JSONObject json1 = JSONObject.fromObject(result);
		assertEquals(json1.getInt("code"),code);
	}
	
	@Test
	public void skuList1() {
		skuList(null,200);
		
	}
	
	@Test
	public void skuList2() {
		skuList("1",200);
	}
	
	@Test
	public void skuList3() {
//		long goodsId=2147483648;
		skuList1(2147483648l,400);
	}
	
	@Test
	public void skuList4() {
		skuList("9999",201);
	}
	
	@Test
	public void skuList5() {
		skuList("\"1\"",400);
	}
}
