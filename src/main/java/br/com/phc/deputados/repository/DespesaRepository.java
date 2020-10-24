package br.com.phc.deputados.repository;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.phc.deputados.model.Despesa;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long>{

	@Query(name = "obtem_valor_total_gasto",
			value = "SELECT sum(valor_documento) as total FROM DESPESA",
			nativeQuery = true)
	public BigDecimal obtemTotalGasto();
	
	
	@Query(name = "obtem_valor_total_gasto_partido",
			value = "SELECT SUM(VALOR_DOCUMENTO) FROM DESPESA AS DES " + 
					"WHERE DES.DEPUTADO_ID " + 
					"IN ( " + 
					"	SELECT DEP.ID FROM DEPUTADO AS DEP WHERE DEP.SIGLA_PARTIDO = UPPER(:partido) " + 
					")",
			nativeQuery = true)
	public BigDecimal obtemTotalGastoPartido(@Param("partido") String partido);
	
	
	@Query(name = "obtem_valor_total_gasto_deputado",
			value = "SELECT SUM(VALOR_DOCUMENTO) AS TOTAL " + 
					"FROM DESPESA " + 
					"WHERE DEPUTADO_ID IN ( " + 
					"	SELECT ID FROM DEPUTADO WHERE UPPER(NOME) = UPPER(:nome) " + 
					")",
			nativeQuery = true)
	public BigDecimal obtemTotalGastoDeputado(@Param("nome") String nome);
	
	
	@Query(name = "obtem_valor_total_gasto_partido",
			value = "SELECT * FROM DESPESA AS DES " + 
					"WHERE DES.DEPUTADO_ID " + 
					"IN ( " + 
					"	SELECT DEP.ID FROM DEPUTADO AS DEP WHERE DEP.SIGLA_PARTIDO = UPPER(:partido) " + 
					")",
			nativeQuery = true)
	public Page<Despesa> listaGastoPartido(@Param("partido") String partido, Pageable pageable);
	
	@Query(name = "obtem_valor_total_gasto_deputado",
			value = "SELECT * FROM DESPESA " + 
					"WHERE DEPUTADO_ID IN ( " + 
					"	SELECT ID FROM DEPUTADO WHERE UPPER(NOME) = UPPER(:nome) " + 
					")",
			nativeQuery = true)
	public Page<Despesa> listaGastoDeputado(@Param("nome") String nome, Pageable pageable);
}
