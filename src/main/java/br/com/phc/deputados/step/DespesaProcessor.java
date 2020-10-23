package br.com.phc.deputados.step;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phc.deputados.model.Despesa;
import br.com.phc.deputados.service.DespesaService;

@Service
public class DespesaProcessor implements ItemProcessor<List<Despesa>, List<Despesa>> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DespesaProcessor.class);

	@Autowired
	private DespesaService despesaService;
	
	@Override
	public List<Despesa> process(List<Despesa> items) throws Exception {
		LOGGER.info("PROCESSOR INICIO -> DespesaProcessor");
		
		items.forEach(item -> {
			Despesa despesa = despesaService.salvarDespesa(item);
			LOGGER.info("PROCESSOR -> despesa salva com sucesso: tipo = {}", despesa.getTipoDespesa());
			LOGGER.info("PROCESSOR -> despesa salva com sucesso: valor = {}", despesa.getValorDocumento());
		});
		LOGGER.info("PROCESSOR FIM -> DespesaProcessor");
		return items;
	}

}
