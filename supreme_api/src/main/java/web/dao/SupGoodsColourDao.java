package web.dao;

import java.util.List;

import web.model.SupGoodsColour;

public interface SupGoodsColourDao {
    int deleteByPrimaryKey(String colourId);

    int insert(SupGoodsColour record);

    int insertSelective(SupGoodsColour record);

    SupGoodsColour selectByPrimaryKey(String colourId);

    int updateByPrimaryKeySelective(SupGoodsColour record);

    int updateByPrimaryKey(SupGoodsColour record);
    
    List<SupGoodsColour> selectByGoodsId (String id);
}