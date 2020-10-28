package br.com.phc.deputados.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
			value = "SELECT SUM(VALOR_DOCUMENTO) AS TOTAL FROM DESPESA",
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
	
	@Query(name = "obtem_menor_data_documento_despesa",
			value = "SELECT MIN(DATA_DOCUMENTO) FROM DESPESA ",
			nativeQuery = true)
	public LocalDate getMinDataDocumento();
	
	@Query(name = "obtem_valor_medio_gasto_diario",
			value = "SELECT ROUND(SUM(VALOR_DOCUMENTO) / :quantidadeDias, 2) FROM DESPESA",
			nativeQuery = true)
	public BigDecimal obtemValorMedioGastoDiario(@Param("quantidadeDias") Long quantidadeDias);

	@Query(name = "obtem_despesas_data_invalida",
			value = "SELECT * FROM DESPESA " + 
					"WHERE DATA_DOCUMENTO > CURRENT_DATE",
			nativeQuery = true)
	public List<Despesa> listaDespesasDataInvalida();
}
