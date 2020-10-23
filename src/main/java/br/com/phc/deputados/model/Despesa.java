package br.com.phc.deputados.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "despesa")
public class Despesa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ano")
	private Integer ano;
	
	@Column(name = "mes")
	private Integer mes;
	
	@Column(name = "tipo_despesa")
	private String tipoDespesa;
	
	@Column(name = "cod_documento")
	private Long codDocumento;
	
	@Column(name = "tipo_documento")
	private String tipoDocumento;
	
	@Column(name = "cod_tipo_documento")
	private Integer codTipoDocumento;
	
	@Column(name = "data_documento")
	@Temporal(TemporalType.DATE)
	private Date dataDocumento;
	
	@Column(name = "num_documento")
	private String numDocumento;
	
	@Column(name = "valor_documento")
	private BigDecimal valorDocumento;
	
	@Column(name = "url_documento")
	private String urlDocumento;
	
	@Column(name = "nome_fornecedor")
	private String nomeFornecedor;
	
	@Column(name = "cnpj_cpf_fornecedor")
	private String cnpjCpfFornecedor;
	
	@Column(name = "valor_liquido")
	private BigDecimal valorLiquido;
	
	@Column(name = "valor_glosa")
	private BigDecimal valorGlosa;
	
	@Column(name = "num_ressarcimento")
	private String numRessarcimento;
	
	@Column(name = "cod_lote")
	private Long codLote;
	
	@Column(name = "parcela")
	private Integer parcela;
	
	@OneToOne
	private Deputado deputado;
}
