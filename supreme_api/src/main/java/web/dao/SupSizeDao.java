package web.dao;

import java.util.List;

import web.model.SupSize;

public interface SupSizeDao {
    int insert(SupSize record);

    int insertSelective(SupSize record);
    
    List<SupSize> selectByGoodsId(String goodsId);
}