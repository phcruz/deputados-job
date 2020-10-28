package br.com.phc.deputados.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import br.com.phc.deputados.dto.DespesaResponseDTO;
import br.com.phc.deputados.dto.ResponseInfoDTO;
import br.com.phc.deputados.exception.BadRequestException;
import br.com.phc.deputados.model.Despesa;
import br.com.phc.deputados.repository.DespesaRepository;

@Service
public class DespesaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DespesaService.class);

	@Autowired
	private DespesaRepository despesaRepository;

	@Autowired
	private RestTemplate restTemplate;

	public DespesaResponseDTO chamadaDespesasAPI(String url) {
		try {
			ResponseEntity<DespesaResponseDTO> response = restTemplate.getForEntity(url, DespesaResponseDTO.class);

			return response.getBody();
		} catch (HttpStatusCodeException e) {
			LOGGER.error("{}", e.getStatusCode());
			LOGGER.error("ERRO: {}", e.getMessage());
			return null;
		}
	}

	public Despesa salvarDespesa(Despesa despesa) {
		return despesaRepository.save(despesa);
	}

	public List<Despesa> listarDespesas() {
		return despesaRepository.findAll();
	}

	public Despesa obterDespesa(Long id) {
		if (Objects.isNull(id))
			throw new BadRequestException("É obrigatório o envio do id para essa consulta.");

		Optional<Despesa> optionalDespesa = despesaRepository.findById(id);
		return optionalDespesa.isPresent() ? optionalDespesa.get() : null;
	}

	public ResponseInfoDTO buscaValorTotalGasto() {
		ResponseInfoDTO dto = new ResponseInfoDTO();
		dto.setValorTotalGasto(despesaRepository.obtemTotalGasto());

		return dto;
	}

	public ResponseInfoDTO buscaValorTotalGastoPartido(String partido) {
		if (Objects.isNull(partido))
			throw new BadRequestException("É obrigatório o envio do partido para essa consulta.");

		ResponseInfoDTO dto = new ResponseInfoDTO();
		dto.setValorTotalGastoPartido(despesaRepository.obtemTotalGastoPartido(partido));

		return dto;
	}

	public ResponseInfoDTO buscaValorTotalGastoDeputado(String nome) {
		if (Objects.isNull(nome))
			throw new BadRequestException("É obrigatório o envio do nome do deputado para essa consulta.");

		ResponseInfoDTO dto = new ResponseInfoDTO();
		dto.setValorTotalGastoDeputado(despesaRepository.obtemTotalGastoDeputado(nome));

		return dto;
	}

	public Page<Despesa> listaGastoPartido(String partido, Pageable pagina) {
		if (Objects.isNull(partido))
			throw new BadRequestException("É obrigatório o envio do partido para essa consulta.");

		return despesaRepository.listaGastoPartido(partido, pagina);
	}

	public Page<Despesa> listaGastoDeputado(String nome, Pageable pagina) {
		if (Objects.isNull(nome))
			throw new BadRequestException("É obrigatório o envio do nome do deputado para essa consulta.");
		return despesaRepository.listaGastoDeputado(nome, pagina);
	}

	public ResponseInfoDTO buscaValorMedioGastoDeputados() {
		LocalDate minDate = despesaRepository.getMinDataDocumento();
		Long quantidadeDias = ChronoUnit.DAYS.between(minDate, LocalDate.now());

		ResponseInfoDTO dto = new ResponseInfoDTO();
		dto.setValorMedioGastoDiario(despesaRepository.obtemValorMedioGastoDiario(quantidadeDias));

		return dto;
	}

	public ResponseInfoDTO listaDespesasDataInvalida() {
		ResponseInfoDTO dto = new ResponseInfoDTO();
		dto.setDespesasInvalidas(despesaRepository.listaDespesasDataInvalida());
		
		return dto;
	}
}
