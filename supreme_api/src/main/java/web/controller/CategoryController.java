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
import web.vo.HomePageVo;

/**
 * 分类接口
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("unused")
@RestController
@RequestMapping("/category")
public class CategoryController {

	private static Logger logger = LoggerFactory.getLogger("COMMLOG");

	@Autowired
	private SupCategoryService categoryService;
	
	/**
	 * 分类查询商品
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public HomePageVo getGoodsByCategoryList(@RequestBody JSONObject jsonObject) {
		
	
		return  categoryService.selectCategoryFetchGoods(jsonObject,jsonObject.getString("categoryName"),jsonObject.getString("categoryId"));
		}
}
