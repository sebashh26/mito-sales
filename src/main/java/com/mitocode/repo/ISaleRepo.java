package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import com.mitocode.dto.ProcedureDTO;
import com.mitocode.model.Sale;
import com.mitocode.service.IProcedureDTO;

public interface ISaleRepo extends IGenericRepo<Sale, Integer> {
	
	@Query(value = "select * from fn_sales()", nativeQuery = true)
	List<Object[]> callProcedure1();
	
//este funciona sin hace nada de mapeos como el de arriba, en el curso no funcionó así ero a mi si me funciono con esta version
//	@Query(value = "select * from fn_sales()", nativeQuery = true)
//	List<ProcedureDTO> callProcedure1();
	
	/**	 
	 * @see document resources in packag resources to find the function and SP
	 */
	@Query(value = "select * from fn_sales()", nativeQuery = true)
	List<IProcedureDTO> callProcedure2();
	
	@Query(name = "Sale.fn_sales", nativeQuery = true)
	List<ProcedureDTO> callProcedure3();
	
	//invoke only procedure no function
	@Procedure(procedureName = "pr_sales")
	void callProcedure4();
}
