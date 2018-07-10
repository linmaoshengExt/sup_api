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

import web.service.MailService;
import web.service.SupGoodsService;
import web.vo.GoodsVo;
import web.vo.OrderVo;

/**
 * 发送订单邮件
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("unused")
@RestController
@RequestMapping("/send")
public class MailController {
	private static Logger logger = LoggerFactory.getLogger("COMMLOG");

	@Autowired
	private MailService mailService;

	@RequestMapping(value = "/mail", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String getGoodsByCategoryList(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
		return mailService.sendMail(jsonObject);
	}
}
