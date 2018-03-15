package QGkuangjia;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;
import com.apitest.core.HttpUtils;
import net.sf.json.JSONObject;

public class GetTransportFee{
	public void getfee(int goodsId,Object addressDetail ,int code){
		
		String subUrl = "/common/getTransportFee";
		Map map = new HashMap();
		map.put("id", goodsId);
		map.put("addressDetail", addressDetail);
		String result = HttpUtils.get(subUrl,map);
		
	
		System.out.println(result);
		JSONObject json1 = JSONObject.fromObject(result);
		assertEquals(json1.getInt("code"),code);
	}

	@Test
	public void fee1(){
		getfee(1,"浙江省_杭州市_滨江区",200);	
	}
	
	@Test
	public void fee2() {
		getfee(0,"浙江省_杭州市_滨江 区",400);
	}
}
