package web.service.serviceImpl;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

import web.dao.SupCategoryDao;
import web.dao.SupGoodsDao;
import web.model.SupGoods;
import web.service.SupCategoryService;
import web.utils.PagedResult;
import web.utils.PriceUtils;
import web.vo.CategoryVo;
import web.vo.HomePageVo;

@Service
public class SupCategoryServiceImpl implements SupCategoryService {

	private static Logger log = LoggerFactory.getLogger("SupCategoryServiceImpl");

	@Autowired
	private SupCategoryDao categoryDao;

	@Autowired
	private  SupGoodsDao goodsDao;

	public HomePageVo selectCategoryFetchGoods(JSONObject jsonObject,String name,String categoryId) {
		HomePageVo homePageVo = new HomePageVo();
		List<SupGoods> supGoodsList =null;
		List<CategoryVo> supCategoryNameList = null;
		PagedResult.pageCommon(jsonObject.getInteger("pageNo"), jsonObject.getInteger("pageSize"));
		if (name == null || name.isEmpty()) {
			homePageVo.setCode(0);
			homePageVo.setMsg("name 不能为空");
			return homePageVo;
		}
		try {
			//判断分类
			if ("all".equals(name)) {
				supGoodsList = goodsDao.goods();
				
			} else {
				supGoodsList = goodsDao.selectByCategory(categoryId);
				
			}
			PriceUtils.getPrice(supGoodsList);
			Page<SupGoods> info = (Page<SupGoods>) supGoodsList;
			homePageVo.setPageNo(info.getPageNum());//当前页
			homePageVo.setPageSize(info.getPageSize());//一页显示多少条
			homePageVo.setTotalPages(info.getPages());//总页数
			homePageVo.setTotalCount((int)info.getTotal());
			homePageVo.setGoodsVoList(supGoodsList);
			supCategoryNameList = categoryDao.selectCategoryName();
			homePageVo.setCategoryList(supCategoryNameList);
			homePageVo.setCode(1);
			homePageVo.setMsg("success");
			log.error("查询分类【" + name + "】    商品成功");
		} catch (Exception e) {
			homePageVo.setCode(0);
			homePageVo.setMsg("fail");
			log.error("查询分类【" + name + "】    商品失败",e);
			throw e;
		}
		return homePageVo;
	}
}
