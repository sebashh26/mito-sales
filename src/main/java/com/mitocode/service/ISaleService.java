package com.mitocode.service;

import java.util.List;

import com.mitocode.dto.ProcedureDTO;
import com.mitocode.model.Sale;

public interface ISaleService extends ICRUD<Sale, Integer>{

	List<ProcedureDTO> callProcedure1();
	
	List<IProcedureDTO> callProcedure2();
}
