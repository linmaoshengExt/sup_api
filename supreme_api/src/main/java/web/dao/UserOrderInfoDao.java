package web.dao;

import web.model.UserOrderInfo;

public interface UserOrderInfoDao {
    int deleteByPrimaryKey(String id);

    int insert(UserOrderInfo record);

    int insertSelective(UserOrderInfo record);

    UserOrderInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserOrderInfo record);

    int updateByPrimaryKey(UserOrderInfo record);
}