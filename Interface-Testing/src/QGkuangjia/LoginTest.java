package QGkuangjia;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;
import com.apitest.core.HttpUtils;

import net.sf.json.JSONObject;

public class LoginTest{
	public void login(Object phoneArea,Object phoneNumber,Object password,int code) {
		JSONObject json=new JSONObject();
		json.element("phoneArea", phoneArea);
		json.element("phoneNumber", phoneNumber);
		json.element("password", password);
		String result=HttpUtils.post("/common/fgadmin/login",json);
		System.out.println(result);
		JSONObject json1 = JSONObject.fromObject(result);
		assertEquals(json1.getInt("code"),code);
	}
	@Test
	public void loginSuccess1() {
		login("86","2000000000118","netease123",200);
	}
	
	@Test
	public void login2() {
		login(86,"2000000000118","netease123",400);
	}
	
	@Test
	public void login3() {
		login("86",2000000000,"netease123",400);
	}
	
	@Test
	public void login4() {
		login(86,"2000000000118",12333,400);
	}
	
	@Test
	public void login5() {
		login(null,"2000000000118","netease123",400);
	}
}
