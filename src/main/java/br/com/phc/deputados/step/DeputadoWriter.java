package br.com.phc.deputados.step;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Service;

import br.com.phc.deputados.model.Deputado;

@Service
public class DeputadoWriter implements ItemWriter<List<Deputado>> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeputadoWriter.class);
	
	@Override
	public void write(List<? extends List<Deputado>> items) throws Exception {
		LOGGER.info("WRITER INICIO -> DeputadoWriter");
		LOGGER.info("WRITER -> Total de deputados salvos: {}", items.get(0).size());
		LOGGER.info("WRITER FIM -> DeputadoWriter");
	}

}
