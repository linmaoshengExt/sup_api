package web.service;


import web.vo.HomePageVo;
import com.alibaba.fastjson.JSONObject;

public interface SupCategoryService {
	HomePageVo selectCategoryFetchGoods(JSONObject jsonObject,String name,String categoryId);
	
}
