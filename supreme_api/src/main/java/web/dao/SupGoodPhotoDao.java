package web.dao;

import java.util.List;

import web.model.SupGoodPhoto;

public interface SupGoodPhotoDao {
    int insert(SupGoodPhoto record);

    int insertSelective(SupGoodPhoto record);
    
    List<SupGoodPhoto>  photoUrlList(String goodsId);
}