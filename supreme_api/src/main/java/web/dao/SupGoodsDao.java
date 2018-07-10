package web.dao;

import java.util.List;

import web.model.SupGoods;

public interface SupGoodsDao {
    int deleteByPrimaryKey(String goodsId);

    int insert(SupGoods record);

    int insertSelective(SupGoods record);

    SupGoods selectByPrimaryKey(String goodsId);

    int updateByPrimaryKeySelective(SupGoods record);

    int updateByPrimaryKey(SupGoods record);
    
    List<SupGoods>  goods();
    
    List<SupGoods>  selectByCategory(String id);
    
    SupGoods  goodsDetails(String goodsId);
    
    SupGoods goodsById(String goodsId);
}