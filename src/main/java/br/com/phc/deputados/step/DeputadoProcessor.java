package br.com.phc.deputados.step;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phc.deputados.model.Deputado;
import br.com.phc.deputados.service.DeputadoService;

@Service
public class DeputadoProcessor implements ItemProcessor<List<Deputado>, List<Deputado>> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeputadoProcessor.class);
	
	@Autowired
	private DeputadoService deputadoService;
	
	@Override
	public List<Deputado> process(List<Deputado> items) throws Exception {
		LOGGER.info("PROCESSOR INICIO -> DeputadoProcessor");

		items.forEach(item -> {
			Deputado deputadoSalvo = deputadoService.salvarDeputado(item);
			LOGGER.info("PROCESSOR -> deputado salvo com sucesso: id = {}", deputadoSalvo.getDeputadoId());
			LOGGER.info("PROCESSOR -> deputado salvo com sucesso: nome = {}", deputadoSalvo.getNome());
		});
		LOGGER.info("PROCESSOR FIM -> DeputadoProcessor");
		return items;
	}

}
