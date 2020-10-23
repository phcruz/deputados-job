package br.com.phc.deputados.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfig {

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String databasePassword;

	@Value("${spring.datasource.hikari.pool-name}")
	private String poolName;

	@Value("${spring.datasource.hikari.minimum-idle}")
	private int minumunIdle;

	@Value("${spring.datasource.hikari.maximum-pool-size}")
	private int maximunPoolSize;

	@Value("${spring.datasource.hikari.connection-timeout}")
	private Long connectionTimeout;

	@Value("${spring.datasource.hikari.idle-timeout}")
	private Long idleTimeout;

	@Value("${spring.datasource.hikari.max-lifetime}")
	private Long maxLifetime;
	
	@Bean
	public DataSource getDataSource() {
		HikariConfig config = new HikariConfig();
		config.setMinimumIdle(minumunIdle);
		config.setMaximumPoolSize(maximunPoolSize);
		config.setPoolName(poolName);
		config.setConnectionTimeout(connectionTimeout);
		config.setIdleTimeout(idleTimeout);
		config.setMaxLifetime(maxLifetime);
		config.setJdbcUrl(url);
		config.setDriverClassName(driverClassName);
		config.setUsername(username);

		return new HikariDataSource(config);
	}
}
