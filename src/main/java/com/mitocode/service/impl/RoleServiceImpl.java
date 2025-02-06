package com.mitocode.service.impl;

import org.springframework.stereotype.Service;

import com.mitocode.model.Role;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IRoleRepo;
import com.mitocode.service.IRoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends CRUDImpl<Role, Integer> implements IRoleService {

	private final IRoleRepo roleRepo;// spring busca la impl de la interface

	@Override
	public IGenericRepo<Role, Integer> getRepo() {
		return roleRepo;
	}

	

}
