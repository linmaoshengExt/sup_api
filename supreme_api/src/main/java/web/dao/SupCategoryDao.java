package web.dao;

import java.util.List;

import web.model.SupCategory;
import web.vo.CategoryVo;

public interface SupCategoryDao {
    int insert(SupCategory record);

    int insertSelective(SupCategory record);
    
    SupCategory selectCategoryFetchGoods(String name);
    
    List<CategoryVo>  selectCategoryName();
}