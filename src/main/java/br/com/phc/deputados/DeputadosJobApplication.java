package br.com.phc.deputados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeputadosJobApplication {

	public static void main(String[] args) {
		/*deixe essa linha para disponivel o tempo todo*/
		SpringApplication.run(DeputadosJobApplication.class, args);
		
		/*para usar um cronJob do kubernetes, comente a linha de cima e utilize este trecho de codigo*/
//		ConfigurableApplicationContext context = SpringApplication.run(DeputadosJobApplication.class, args);
//		SpringApplication.exit(context);
	}

}
