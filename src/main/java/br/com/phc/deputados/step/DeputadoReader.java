package br.com.phc.deputados.step;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.phc.deputados.dto.DeputadoResponseDTO;
import br.com.phc.deputados.model.Deputado;
import br.com.phc.deputados.model.Info;
import br.com.phc.deputados.service.DeputadoService;
import br.com.phc.deputados.util.TipoLinkIndicador;

@Service
public class DeputadoReader implements ItemReader<List<Deputado>> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeputadoReader.class);

	@Value("${base.url.dados.deputado}")
	private String baseUrl;
	private String nextUrl;

	private boolean isPrimeiraChamada = Boolean.TRUE;

	@Autowired
	private DeputadoService deputadoService;

	@Override
	public List<Deputado> read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		LOGGER.info("READER INICIO -> DeputadoReader");
		List<Deputado> listDeputados = null;

		String url = getUrl();
		if (Objects.nonNull(url)) {
			DeputadoResponseDTO deputados = deputadoService.chamadaDeputadosAPI(url);
			
			if (Objects.nonNull(deputados)) {
				LOGGER.info("READER -> quantidade de items: {}", deputados.getDados().size());
				atualizaNextUrl(deputados);
				listDeputados = deputados.getDados().isEmpty() ? null : deputados.getDados();	
			}
			
		}

		LOGGER.info("READER FIM -> DeputadoReader");
		return listDeputados;
	}

	private String getUrl() {
		if (isPrimeiraChamada) {
			isPrimeiraChamada = Boolean.FALSE;
			return baseUrl;
		} else {
			return nextUrl;
		}
	}

	private void atualizaNextUrl(DeputadoResponseDTO deputados) {
		if (Objects.nonNull(deputados)) {
			Optional<Info> optionalInfo = deputados.getLinks()
					.stream()
					.filter(item -> item.getRel().equals(TipoLinkIndicador.NEXT.toString()))
					.findFirst();
			nextUrl = optionalInfo.isPresent() ? optionalInfo.get().getHref() : null;
		} else {
			nextUrl = null;
		}
	}
}
