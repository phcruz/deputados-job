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
	
	@ApiOperation(value = "Recupera o valor total gasto pelos deputados no ano corrente")
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
	
	@ApiOperation(value = "Recupera o valor total gasto por um partido")
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
	
	@ApiOperation(value = "Recupera o valor total gasto por um deputado")
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
	
	@ApiOperation(value = "Lista as despesas de um partido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Page.class),
			@ApiResponse(code = 400, message = "Bad request", response = StandardError.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
			@ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
			@ApiResponse(code = 404, message = "Not found", response = StandardError.class),
			@ApiResponse(code = 500, message = "Internal server error", response = StandardError.class) })
	@GetMapping(path = "/lista/partido")
	public ResponseEntity<Page<Despesa>> listaGastoPartido(@RequestParam("partido") String partido,
			@RequestParam(value = "pagina",required = true, defaultValue = "1") Integer pagina,
			@RequestParam(value = "qtdItens", required = true, defaultValue = "20") Integer quantidade) {
		
		Pageable page = PageRequest.of(pagina, quantidade, Sort.by("VALOR_DOCUMENTO").descending());
		
		return ResponseEntity.ok().body(despesaService.listaGastoPartido(partido, page));
	}
	
	@ApiOperation(value = "Lista as despesas de um deputado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Page.class),
			@ApiResponse(code = 400, message = "Bad request", response = StandardError.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
			@ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
			@ApiResponse(code = 404, message = "Not found", response = StandardError.class),
			@ApiResponse(code = 500, message = "Internal server error", response = StandardError.class) })
	@GetMapping(path = "/lista/deputado")
	public ResponseEntity<Page<Despesa>> listaGastoDeputado(@RequestParam("nome") String nome,
			@RequestParam(value = "pagina",required = true, defaultValue = "1") Integer pagina,
			@RequestParam(value = "qtdItens", required = true, defaultValue = "20") Integer quantidade) {
		
		Pageable page = PageRequest.of(pagina, quantidade, Sort.by("VALOR_DOCUMENTO").descending());
		
		return ResponseEntity.ok().body(despesaService.listaGastoDeputado(nome, page));
	}
}
