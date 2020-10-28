package br.com.phc.deputados.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.phc.deputados.dto.ResponseInfoDTO;
import br.com.phc.deputados.exception.StandardError;
import br.com.phc.deputados.model.Despesa;
import br.com.phc.deputados.service.DespesaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("API de Despesas")
@RestController
@RequestMapping("/api/v1/despesas")
public class DespesaController {

	@Autowired
	private DespesaService despesaService;
	
	@ApiOperation(value = "Recupera o valor total gasto pelos deputados no ano corrente",
			notes = "Endpoint responsável por somar todas as despesas cadastradas e disponibilizadas pela API de dados públicos da câmara dos deputados referentes ao ano corrente.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = ResponseInfoDTO.class),
			@ApiResponse(code = 400, message = "Bad request", response = StandardError.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
			@ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
			@ApiResponse(code = 404, message = "Not found", response = StandardError.class),
			@ApiResponse(code = 500, message = "Internal server error", response = StandardError.class) })
	@GetMapping(path = "/total")
	public ResponseEntity<ResponseInfoDTO> buscaValorTotalGasto() {
		return ResponseEntity.ok().body(despesaService.buscaValorTotalGasto());
	}
	
	@ApiOperation(value = "Recupera o valor total gasto por um partido",
			notes = "Endpoint responsável por somar o total de gastos de um partido. A sigla do partido deve ser informada na requisição, caso contrário haverá um erro. "
					+ "O retorno será a soma de todos os gastos do partido informado na requisição.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = ResponseInfoDTO.class),
			@ApiResponse(code = 400, message = "Bad request", response = StandardError.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
			@ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
			@ApiResponse(code = 404, message = "Not found", response = StandardError.class),
			@ApiResponse(code = 500, message = "Internal server error", response = StandardError.class) })
	@GetMapping(path = "/total/partido")
	public ResponseEntity<ResponseInfoDTO> buscaValorTotalGastoPartido(@RequestParam("partido") String partido) {
		return ResponseEntity.ok().body(despesaService.buscaValorTotalGastoPartido(partido));
	}
	
	@ApiOperation(value = "Recupera o valor total gasto por um deputado",
			notes = "Endpoint responsável por somar todos os gastos de um determinado deputado, no ano corrente. <b>Importante ressaltar que os dados são disponibilizados pela API de dados públicos da câmara dos deputados</b>, e fazem referência ao ano corrente.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = ResponseInfoDTO.class),
			@ApiResponse(code = 400, message = "Bad request", response = StandardError.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
			@ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
			@ApiResponse(code = 404, message = "Not found", response = StandardError.class),
			@ApiResponse(code = 500, message = "Internal server error", response = StandardError.class) })
	@GetMapping(path = "/total/deputado")
	public ResponseEntity<ResponseInfoDTO> buscaValorTotalGastoDeputado(@RequestParam("nome") String nome) {
		return ResponseEntity.ok().body(despesaService.buscaValorTotalGastoDeputado(nome));
	}
	
	@ApiOperation(value = "Recupera o valor medio de gasto diário dos deputados",
			notes = "Endpoint responsável por verificar o valor médio diário de gastos de todos os deputados. <b>Importante ressaltar que esses dados são disponibilizados pela API de dados públicos da câmara dos deputados</b>, e fazem referência ao ano corrente, "
					+ "porém na última execução do projeto, notamos que as primeiras despesas datam de abril de 2020, e algumas despesas estão lançadas com datas absurdas como 2201-01-06 e 2209-10-21, que possivelmente ainda serão corrigidas na API da câmara.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = ResponseInfoDTO.class),
			@ApiResponse(code = 400, message = "Bad request", response = StandardError.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
			@ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
			@ApiResponse(code = 404, message = "Not found", response = StandardError.class),
			@ApiResponse(code = 500, message = "Internal server error", response = StandardError.class) })
	@GetMapping(path = "/media")
	public ResponseEntity<ResponseInfoDTO> buscaValorMedioGastoDeputados() {
		return ResponseEntity.ok().body(despesaService.buscaValorMedioGastoDeputados());
	}
	
	@ApiOperation(value = "Lista as despesas de um partido",
			notes = "Endpoint responsável por listar todas as despesas de um partido específico no ano corrente. A busca é feita pela sigla do partido, sendo necessário informar corretamente a sigla do partido no qual deseja consultar as despesas. "
					+ "A listagem utiliza paginação para exibir os resultados.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Page.class),
			@ApiResponse(code = 400, message = "Bad request", response = StandardError.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
			@ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
			@ApiResponse(code = 404, message = "Not found", response = StandardError.class),
			@ApiResponse(code = 500, message = "Internal server error", response = StandardError.class) })
	@GetMapping(path = "/partido")
	public ResponseEntity<Page<Despesa>> listaGastoPartido(@RequestParam("partido") String partido,
			@RequestParam(value = "pagina",required = true, defaultValue = "1") Integer pagina,
			@RequestParam(value = "qtdItens", required = true, defaultValue = "20") Integer quantidade) {
		
		Pageable page = PageRequest.of(pagina, quantidade, Sort.by("VALOR_DOCUMENTO").descending());
		
		return ResponseEntity.ok().body(despesaService.listaGastoPartido(partido, page));
	}
	
	@ApiOperation(value = "Lista as despesas de um deputado",
			notes = "Endpoint responsável por listar todas as despesas de um deputado específico no ano corrente. A busca é feita por nome, sendo necessário informar corretamente o nome do deputado no qual deseja consultar as despesas. "
					+ "A listagem utiliza paginação para exibir os resultados.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Page.class),
			@ApiResponse(code = 400, message = "Bad request", response = StandardError.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
			@ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
			@ApiResponse(code = 404, message = "Not found", response = StandardError.class),
			@ApiResponse(code = 500, message = "Internal server error", response = StandardError.class) })
	@GetMapping(path = "/deputado")
	public ResponseEntity<Page<Despesa>> listaGastoDeputado(@RequestParam("nome") String nome,
			@RequestParam(value = "pagina",required = true, defaultValue = "1") Integer pagina,
			@RequestParam(value = "qtdItens", required = true, defaultValue = "20") Integer quantidade) {
		
		Pageable page = PageRequest.of(pagina, quantidade, Sort.by("VALOR_DOCUMENTO").descending());
		
		return ResponseEntity.ok().body(despesaService.listaGastoDeputado(nome, page));
	}
	
	@ApiOperation(value = "Lista as despesas lançadas com data inválida",
			notes = "Endpoint responsável por listar despesas lançadas com datas inválidas e totalmente erradas. A lista de despesas retornará todos os objetos com datas de despesa sendo maior que a data atual, em alguns casos <b>o erro é bizarro</b>. "
					+ "<b>Importante ressaltar que os dados são disponibilizados pela API de dados públicos da câmara dos deputados</b>.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = ResponseInfoDTO.class),
			@ApiResponse(code = 400, message = "Bad request", response = StandardError.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
			@ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
			@ApiResponse(code = 404, message = "Not found", response = StandardError.class),
			@ApiResponse(code = 500, message = "Internal server error", response = StandardError.class) })
	@GetMapping(path = "/invalida")
	public ResponseEntity<ResponseInfoDTO> listaDespesasDataInvalida() {
		return ResponseEntity.ok().body(despesaService.listaDespesasDataInvalida());
	}
}
