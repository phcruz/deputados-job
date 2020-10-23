package br.com.phc.deputados.dto;

import java.io.Serializable;
import java.util.List;

import br.com.phc.deputados.model.Deputado;
import br.com.phc.deputados.model.Info;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeputadoResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Deputado> dados;
	private List<Info> links;

}
