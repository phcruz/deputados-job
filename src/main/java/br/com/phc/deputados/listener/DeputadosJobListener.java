package br.com.phc.deputados.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

public class DeputadosJobListener extends JobExecutionListenerSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeputadosJobListener.class);

	@Override
	public void afterJob(JobExecution jobExecution) {
		LOGGER.info("ID: {}", jobExecution.getId());
		LOGGER.info("JOB ID: {}", jobExecution.getJobId());
		LOGGER.info("END TIME: {}", jobExecution.getEndTime());
		LOGGER.info("STATUS: {}", jobExecution.getStatus());
		LOGGER.info("VERSION: {}", jobExecution.getVersion());
	}
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		LOGGER.info("ID: {}", jobExecution.getId());
		LOGGER.info("JOB ID: {}", jobExecution.getJobId());
		LOGGER.info("START TIME: {}", jobExecution.getStartTime());
		LOGGER.info("STATUS: {}", jobExecution.getStatus());
		LOGGER.info("VERSION: {}", jobExecution.getVersion());
	}
}
