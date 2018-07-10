package web.service;

import com.alibaba.fastjson.JSONObject;

public interface MailService {

	String sendMail(JSONObject jsonObject);

	void sendMail1(String mail, String content); 
}
