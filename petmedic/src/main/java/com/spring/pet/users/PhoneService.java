package com.spring.pet.users;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Component
public class PhoneService {
    
	public void certifiedPhoneNumber(String users_tel, String numStr) {
	      System.out.println("휴대폰인증 서비스 탔냐");
//	       String api_key = "NCS6ZMTYSWCCW8ZY";
//	       String api_secret = "YRKV9GAHE8QO0AWEU0ZMLL1XHOA3HKLR";
	       String api_key = "NCSAZJYGRDSMI1BL";
	       String api_secret = "JLJDL3BZTZJMYXTMK812L4MNYQO1BCI5";
	       Message coolsms = new Message(api_key, api_secret);

	       // 4 params(to, from, type, text) are mandatory. must be filled
	       HashMap<String, String> params = new HashMap<String, String>();
	       System.out.println("서비스 폰넘버 : " + users_tel);
	       params.put("to", users_tel);    // 수신전화번호
//	       params.put("from", "01040881054");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
	       params.put("from", "01036289543");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
	       params.put("type", "SMS");
	       params.put("text", "[펫메딕] 인증번호는" + "["+numStr+"]" + "입니다."); // 문자 내용 입력
	       params.put("app_version", "test app 1.2"); // application name and version

	       try {
	           JSONObject obj = (JSONObject) coolsms.send(params);
	           System.out.println(obj.toString());
	         } catch (CoolsmsException e) {
	           System.out.println(e.getMessage());
	           System.out.println(e.getCode());
	         }
	   }
   
}
