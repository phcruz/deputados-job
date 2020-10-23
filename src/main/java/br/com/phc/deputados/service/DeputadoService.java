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

import br.com.phc.deputados.dto.DeputadoResponseDTO;
import br.com.phc.deputados.model.Deputado;
import br.com.phc.deputados.repository.DeputadoRepository;

@Service
public class DeputadoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeputadoService.class);
	
	@Autowired
	private DeputadoRepository deputadoRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public DeputadoResponseDTO chamadaDeputadosAPI(String url) {
		try {
			ResponseEntity<DeputadoResponseDTO> response = restTemplate.getForEntity(url, DeputadoResponseDTO.class);
			
			return response.getBody();
		} catch (HttpStatusCodeException e) {
			LOGGER.error("{}", e.getStatusCode());
			LOGGER.error("ERRO: {}", e.getMessage());
			return null;
		}
	}

	public Deputado salvarDeputado(Deputado deputado) {
		return deputadoRepository.save(deputado);
	}
	
	public List<Deputado> listarDeputados() {
		return deputadoRepository.findAll();
	}
	
	public Deputado obterDeputado(Long id) {
		Optional<Deputado> optionalDeputado = deputadoRepository.findById(id);
		return optionalDeputado.isPresent() ? optionalDeputado.get() : null;
	}
}
