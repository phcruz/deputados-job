package br.com.phc.deputados.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import br.com.phc.deputados.dto.DespesaResponseDTO;
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
		Optional<Despesa> optionalDespesa = despesaRepository.findById(id);
		return optionalDespesa.isPresent() ? optionalDespesa.get() : null;
	}
}
