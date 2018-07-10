package web.service.serviceImpl;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import web.constants.MailInfo;
import web.dao.OrderDao;
import web.dao.UserInfoDao;
import web.dao.UserOrderInfoDao;
import web.model.Order;
import web.model.UserInfo;
import web.model.UserOrderInfo;
import web.service.OrderService;
import web.utils.IpUntil;
import web.utils.MailSendUtil;
import web.utils.RandUtils;
import web.utils.UuidUntil;
import web.vo.OrderVo;

@Service
public class OrderServiceImpl implements OrderService {

	private static Logger log = LoggerFactory.getLogger("OrderServiceImpl");

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private UserOrderInfoDao userOrderInfoDao;

	@Override
	public OrderVo getOrder(JSONObject jsonObject,HttpServletRequest request) {
		OrderVo orderVo = new OrderVo();
		String email = jsonObject.getString("email");
		// 通过邮件查询是否是新用户
		try {
			int count = userInfoDao.selectNums(email);
			if (count < 1) {
				UserInfo user = new UserInfo();
				user.setId(UuidUntil.getUUID());
				user.setCity(jsonObject.getString("city"));
				user.setCountry(jsonObject.getString("country"));
				user.setEmail(email);
				user.setFirstName(jsonObject.getString("firstName"));
				user.setLastName(jsonObject.getString("lastName"));
				user.setPhoneNumber(jsonObject.getString("phone"));
				user.setPostCode(jsonObject.getString("postCode"));
				user.setSuite(jsonObject.getString("suite"));
				user.setIp(IpUntil.getIp(request));
				user.setUpdateTime(new Date());
				user.setCreatTime(new Date());
				userInfoDao.insert(user);
				addOrder(user, jsonObject);
			} else {
				UserInfo user = userInfoDao.selectByEmail(email);
				addOrder(user, jsonObject);
			}
			
		} catch (Exception e) {
			log.error("------下单异常-------" + e.toString());
			orderVo.setCode(0);
			orderVo.setMsg("下单异常");
		}
		orderVo.setCode(1);
		orderVo.setMsg("下单成功");
		return orderVo;
	}

	public void addOrder(UserInfo user, JSONObject jsonObject) {
		JSONObject object = null;
		Order order = null;
		StringBuffer bufGoodsId=new StringBuffer();
		StringBuffer bufGoodsName=new StringBuffer();
		StringBuffer bufSizeName=new StringBuffer();
		JSONArray jsonArray = jsonObject.getJSONArray("goodslist");
		for (int i = 0; i < jsonArray.size(); i++) {
			object = jsonArray.getJSONObject(i);
			order = new Order();
			order.setId(UuidUntil.getUUID());
			order.setStatus(100);
			String goodsId = object.getString("goodsId");
			String goodsName = object.getString("goodsName");
			String sizeName = object.getString("sizeName");
			order.setGoodsId(goodsId);
			order.setGoodsName(goodsName);
			order.setSizeId(object.getString("sizeId"));
			order.setSizeName(sizeName);
			order.setGoodsPrices(object.getLong("goodsPrice"));
			order.setUserId(user.getId());
			order.setUpdateTime(new Date());
			order.setCreateTime(new Date());
			order.setOrderNumber(RandUtils.rand());
			orderDao.insertSelective(order);
			bufGoodsId.append(goodsId+",");
			bufGoodsName.append(goodsName+",");
			bufSizeName.append(sizeName+",");
		}
		UserOrderInfo userOrderInfo = new UserOrderInfo();
		userOrderInfo.setId(UuidUntil.getUUID());
		userOrderInfo.setGoodsId(bufGoodsId.toString());
		userOrderInfo.setGoodsName(bufGoodsName.toString());
		userOrderInfo.setTotalSum(jsonObject.getLong("totalSum"));
		userOrderInfo.setOrderDetails(bufGoodsName.toString()+","+"   "+bufSizeName.toString()+",");
		userOrderInfoDao.insert(userOrderInfo);
	}
	
	
	//发送邮件
	public static void sendMail(String mail ,String content ) {
        String title = "Dear use, please check your order";
        MailInfo info = new MailInfo();
        info.setToAddress(mail);//发送对象的邮箱
        info.setSubject(title);
        info.setContent(content);
        try {

            MailSendUtil.sendHtmlMail(info);
        } catch (Exception e) {
            System.out.print("'"+title+"'的邮件发送失败！");
            e.printStackTrace();
        }
	}
}
