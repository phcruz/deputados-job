package br.com.phc.deputados.dto;

import java.io.Serializable;
import java.util.List;

import br.com.phc.deputados.model.Despesa;
import br.com.phc.deputados.model.Info;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DespesaResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Despesa> dados;
	private List<Info> links;
	
}
