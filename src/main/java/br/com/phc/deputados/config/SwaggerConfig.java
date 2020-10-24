package br.com.phc.deputados.config;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final String BASE_PACKAGE = "br.com.phc.deputados.controller";
	private static final String API_TITULO = "API de dados de Deputados e Despesas de deputados";
	private static final String API_DESCRICAO = "REST API para obter informações sobre deputados e as despesas de cada deputado. A obtenção dos dados é feita via batch que roda todos os dias a meia noite e na inicialização da aplicação.";
	private static final String API_VERSAO = "1.0.0";
	private static final String CONTATO_NOME = "Paulo Henrique da Cruz";
	private static final String CONTATO_GITHUB = "https://github.com/phcruz";
	private static final String CONTATO_EMAIL = "henryque_phc@yahoo.com.br";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(basePackage(BASE_PACKAGE))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(buildApiInfo());
	}

	private ApiInfo buildApiInfo() {
		return new ApiInfoBuilder()
				.title(API_TITULO)
				.description(API_DESCRICAO)
				.version(API_VERSAO)
				.contact(new Contact(CONTATO_NOME, CONTATO_GITHUB, CONTATO_EMAIL))
				.build();
	}
}
