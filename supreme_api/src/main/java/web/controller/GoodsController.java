package web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import web.dao.SupGoodsDao;
import web.model.SupGoods;
import web.service.SupCategoryService;
import web.service.SupGoodsService;
import web.vo.GoodsVo;
import web.vo.HomePageVo;

/**
 * 商品接口
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("unused")
@RestController
@RequestMapping("/goods")
public class GoodsController {

	private static Logger logger = LoggerFactory.getLogger("COMMLOG");

	@Autowired
	private SupGoodsService goodsService;

	
	
	/**
	 * 商品详情
	 */
	@RequestMapping(value = "/details", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public GoodsVo getGoodsByCategoryList(@RequestBody JSONObject jsonObject) {
		String id=jsonObject.getString("id");
		return  goodsService.getById(id);
	}
}
