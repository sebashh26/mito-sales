package com.mitocode.service.impl;

import org.springframework.stereotype.Service;

import com.mitocode.model.User;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IUserRepo;
import com.mitocode.service.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends CRUDImpl<User, Integer> implements IUserService {

	private final IUserRepo userRepo;// spring busca la impl de la interface

	@Override
	public IGenericRepo<User, Integer> getRepo() {
		return userRepo;
	}

	

}
