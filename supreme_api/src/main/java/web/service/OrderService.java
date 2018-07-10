package web.service;


import web.vo.OrderVo;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

public interface OrderService {
	OrderVo getOrder(JSONObject jsonObject,HttpServletRequest request);
	
}
