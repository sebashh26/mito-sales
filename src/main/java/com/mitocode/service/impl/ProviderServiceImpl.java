package com.mitocode.service.impl;

import org.springframework.stereotype.Service;

import com.mitocode.model.Provider;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IProviderRepo;
import com.mitocode.service.IProviderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl extends CRUDImpl<Provider, Integer> implements IProviderService {

	private final IProviderRepo providerRepo;// spring busca la impl de la interface

	@Override
	public IGenericRepo<Provider, Integer> getRepo() {
		return providerRepo;
	}

	

}
