package br.com.phc.deputados.step;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.phc.deputados.dto.DespesaResponseDTO;
import br.com.phc.deputados.model.Deputado;
import br.com.phc.deputados.model.Despesa;
import br.com.phc.deputados.model.Info;
import br.com.phc.deputados.service.DeputadoService;
import br.com.phc.deputados.service.DespesaService;
import br.com.phc.deputados.util.TipoLinkIndicador;

@Service
public class DespesaReader implements ItemReader<List<Despesa>> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DespesaReader.class);

	@Value("${base.url.dados.despesa}")
	private String baseUrl;
	private String nextUrl;

	private Deputado deputado;
	private boolean isPrimeiraChamada = Boolean.TRUE;
	private Long posicao = 1L;

	@Autowired
	private DespesaService despesaService;

	@Autowired
	private DeputadoService deputadoService;

	@Override
	public List<Despesa> read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		LOGGER.info("READER INICIO -> DespesaReader");
		List<Despesa> listDespesas = null;

		String url = getUrl();
		if (Objects.nonNull(url)) {
			DespesaResponseDTO despesas = despesaService.chamadaDespesasAPI(url);

			if (Objects.nonNull(despesas)) {
				LOGGER.info("READER -> quantidade de items: {}", despesas.getDados().size());
				atualizaNextUrl(despesas);
				listDespesas = despesas.getDados();
			}
		}

		if (Objects.nonNull(listDespesas)) {
			listDespesas = listDespesas
					.stream()
					.map(item -> {
						item.setDeputado(deputado);
						return item;
					}).collect(Collectors.toList());
		}

		LOGGER.info("READER FIM -> DespesaReader");
		return listDespesas;
	}

	private String getUrl() {
		if (isPrimeiraChamada) {
			isPrimeiraChamada = Boolean.FALSE;

			deputado = deputadoService.obterDeputado(posicao);
			posicao++;

			return Objects.isNull(deputado) ? null : baseUrl.replace("{id}", String.valueOf(deputado.getDeputadoId()));
		} else {
			return nextUrl;
		}
	}

	private void atualizaNextUrl(DespesaResponseDTO despesas) {
		if (Objects.nonNull(despesas)) {
			Optional<Info> optionalInfo = despesas.getLinks()
					.stream()
					.filter(item -> item.getRel().equals(TipoLinkIndicador.NEXT.toString()))
					.findFirst();
			nextUrl = optionalInfo.isPresent() ? optionalInfo.get().getHref() : reinicializaFluxo();
		} else {
			nextUrl = null;
		}
	}

	private String reinicializaFluxo() {
		isPrimeiraChamada = Boolean.TRUE;
		return getUrl();
	}

}
