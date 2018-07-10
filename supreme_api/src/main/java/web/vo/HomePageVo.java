package web.vo;

import java.util.List;

import web.constants.PageEntity;
import web.model.SupGoods;

public class HomePageVo extends PageEntity {
	public List<CategoryVo> categoryList ;
	
	public List<SupGoods>    goodsVoList;
	
	public int  code;
	
	public String msg;
	
	

	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<CategoryVo> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CategoryVo> categoryList) {
		this.categoryList = categoryList;
	}

	public List<SupGoods> getGoodsVoList() {
		return goodsVoList;
	}

	public void setGoodsVoList(List<SupGoods> goodsVoList) {
		this.goodsVoList = goodsVoList;
	}

	 
	
}

