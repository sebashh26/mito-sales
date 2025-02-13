package com.mitocode.service;

import java.util.List;
import java.util.Map;

import com.mitocode.dto.ProcedureDTO;
import com.mitocode.model.Sale;

public interface ISaleService extends ICRUD<Sale, Integer>{

	List<ProcedureDTO> callProcedure1();
	
	List<IProcedureDTO> callProcedure2();
	
	List<ProcedureDTO> callProcedure3();
	
	void callProcedure4();
	
	//obtener la venta mayor
	Sale getSaleMostExpensive();
	
	//nombre del mejor venedor
	String getBestSellerPerson();
	
	//contar la cantidad de ventas por vendedor
	Map<String, Long> getSalesCountBySeller();
	
	//producto mas vendido
	Map<String, Double> getMostSellerProduct();
	
	
}
