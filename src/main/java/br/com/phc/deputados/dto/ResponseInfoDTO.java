package br.com.phc.deputados.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.phc.deputados.model.Despesa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseInfoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private BigDecimal valorTotalGasto;
	private BigDecimal valorTotalGastoPartido;
	private BigDecimal valorTotalGastoDeputado;
	
	private List<Despesa> despesasPartido;
	private List<Despesa> despesasDeputado;
}
