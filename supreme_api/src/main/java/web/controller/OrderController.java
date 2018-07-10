package web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import web.dao.MengIpDao;
import web.model.MengIp;
import web.service.OrderService;
import web.service.SupGoodsService;
import web.utils.IpUntil;
import web.vo.OrderVo;

/**
 * 商品接口
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("unused")
@RestController
@RequestMapping("/order")
public class OrderController {

	private static Logger logger = LoggerFactory.getLogger("COMMLOG");

	@Autowired
	private OrderService orderService;
	
	
	@Autowired
    private MengIpDao mengIpDao;
	/**
	 * 下单
	 */
	@RequestMapping(value = "/shop", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public OrderVo getGoodsByCategoryList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		return orderService.getOrder(jsonObject,request);
	}
	
	@RequestMapping(value = "/mengdi", method = RequestMethod.GET )
	public String mengdi(HttpServletRequest request) {
		
		System.err.println(IpUntil.getIp(request));
		MengIp meng = new MengIp();
		meng.setIp(IpUntil.getIp(request));
		mengIpDao.insert(meng);
		return "success";
	}
}
