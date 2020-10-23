package br.com.phc.deputados.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "deputado")
public class Deputado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "deputado_id")
	private Long deputadoId;

	@Column(name = "uri")
	private String uri;

	@Column(name = "nome")
	private String nome;

	@Column(name = "sigla_partido")
	private String siglaPartido;

	@Column(name = "uri_partido")
	private String uriPartido;

	@Column(name = "sigla_uf")
	private String siglaUf;

	@Column(name = "id_legislatura")
	private Long idLegislatura;

	@Column(name = "url_foto")
	private String urlFoto;

	@Column(name = "email")
	private String email;

	@JsonProperty(value = "deputadoId", access = JsonProperty.Access.READ_ONLY)
	public Long getDeputadoId() {
		return deputadoId;
	}
	
	@JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
	public void setDeputadoId(Long deputadoId) {
		this.deputadoId = deputadoId;
	}

	
	
}
