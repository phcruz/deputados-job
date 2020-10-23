package br.com.phc.deputados.config;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.phc.deputados.listener.DeputadosJobListener;
import br.com.phc.deputados.model.Deputado;
import br.com.phc.deputados.model.Despesa;
import br.com.phc.deputados.step.DeputadoProcessor;
import br.com.phc.deputados.step.DeputadoReader;
import br.com.phc.deputados.step.DeputadoWriter;
import br.com.phc.deputados.step.DespesaProcessor;
import br.com.phc.deputados.step.DespesaReader;
import br.com.phc.deputados.step.DespesaWriter;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DeputadoProcessor deputadoProcessor;
	@Autowired
	private DeputadoReader deputadoReader;
	@Autowired
	private DeputadoWriter deputadoWriter;
	
	@Autowired
	private DespesaProcessor despesaProcessor;
	@Autowired
	private DespesaReader despesaReader;
	@Autowired
	private DespesaWriter despesaWriter;
	
	@Bean
	public JobExecutionListener listener() {
		return new DeputadosJobListener();
	}
	
	@Bean
	@Qualifier("jobDeputado")
	public Job executaDeputadoJob() {
		return jobBuilderFactory.get("executaDeputadoJob")
				.incrementer(new RunIdIncrementer())
				.listener(this.listener())
				.flow(this.flowDeputadoJob())
				.next(this.flowDespesasDeputadoJob())
				.end()
				.build();
	}
	
	@Bean
	public Step flowDeputadoJob() {
		return stepBuilderFactory.get("flowDeputadoJob")
				.<List<Deputado>, List<Deputado>>chunk(1)
				.reader(deputadoReader)
				.processor(deputadoProcessor)
				.writer(deputadoWriter)
				.build();
	}
	
	@Bean
	public Step flowDespesasDeputadoJob() {
		return stepBuilderFactory.get("flowDespesasDeputadoJob")
				.<List<Despesa>, List<Despesa>>chunk(1)
				.reader(despesaReader)
				.processor(despesaProcessor)
				.writer(despesaWriter)
				.build();
	}
}
