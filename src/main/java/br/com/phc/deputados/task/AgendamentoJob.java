package br.com.phc.deputados.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class AgendamentoJob {

private static final Logger LOGGER = LoggerFactory.getLogger(AgendamentoJob.class);
	
	private static final String TIME_ZONE = "America/Sao_Paulo";
	private static final String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy hh:mm:ss";
	
	@Autowired
	@Qualifier("jobDeputado")
	private Job job;

	@Autowired
	private JobLauncher jobLauncher;

	/* Vai rodar todos os dias a meia noite */
	@Scheduled(cron = "0 0 0 * * 1-7", zone = TIME_ZONE)
	public void executaTarefaAgendada() {
		LOGGER.info("Executando tarefa agendada -> {}", this.formatarDataStringMask());
		JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        try {
			jobLauncher.run(job, params);
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
			LOGGER.error("ERRO: {}", e.getMessage());
		}
	}
	
	public String formatarDataStringMask() {
		DateFormat formatter = new SimpleDateFormat(DD_MM_YYYY_HH_MM_SS);
		return formatter.format(new Date());
	}
}
