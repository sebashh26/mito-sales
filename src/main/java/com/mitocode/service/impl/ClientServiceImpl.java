package com.mitocode.service.impl;

import org.springframework.stereotype.Service;

import com.mitocode.model.Client;
import com.mitocode.repo.IClientRepo;
import com.mitocode.repo.IGenericRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl extends CRUDImpl<Client, Integer> {

	private final IClientRepo clientRepo;// spring busca la impl de la interface

	@Override
	public IGenericRepo<Client, Integer> getRepo() {
		return clientRepo;
	}

	

}
