package br.com.phc.deputados.step;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Service;

import br.com.phc.deputados.model.Despesa;

@Service
public class DespesaWriter implements ItemWriter<List<Despesa>> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DespesaWriter.class);

	@Override
	public void write(List<? extends List<Despesa>> items) throws Exception {
		LOGGER.info("WRITER INICIO -> DespesaWriter");
		LOGGER.info("WRITER -> Total de despesas salvas: {}", items.get(0).size());
		LOGGER.info("WRITER FIM -> DespesaWriter");
	}

}
