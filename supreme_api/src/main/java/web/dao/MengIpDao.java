package web.dao;

import web.model.MengIp;

public interface MengIpDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MengIp record);

    int insertSelective(MengIp record);

    MengIp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MengIp record);

    int updateByPrimaryKey(MengIp record);
}