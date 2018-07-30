package web.dao;

import web.model.UserInfo;

public interface UserInfoDao {
	   int deleteByPrimaryKey(String id);

	    int insert(UserInfo record);

	    int insertSelective(UserInfo record);

	    UserInfo selectByPrimaryKey(String id);

	    int updateByPrimaryKeySelective(UserInfo record);

	    int updateByPrimaryKey(UserInfo record);
	    
	    int selectNums(String email);  
	    
	    UserInfo selectByEmail(String email);;
}