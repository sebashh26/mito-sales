package com.mitocode.repo;

import com.mitocode.model.User;

public interface IUserRepo extends IGenericRepo<User, Integer> {
	
	//@Query("FROM User y where u.userName= :userName")
	User findOneByUserName(String userName);
}
