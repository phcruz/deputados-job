package br.com.phc.deputados.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomClientHttpRequestInterceptor.class);
	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		logRequestDetails(request);
		
		return execution.execute(request, body);
	}

	private void logRequestDetails(HttpRequest request) {
		LOGGER.info("Headers: {}", request.getHeaders());
		LOGGER.info("Request Method: {}", request.getMethod());
		LOGGER.info("Request URI: {}", request.getURI());
	}
	
	@SuppressWarnings("unused")
	private ClientHttpResponse logBodyDetails(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		logRequestDetails(request);
		
		LOGGER.info("Request body: {}", new String(body, StandardCharsets.UTF_8));
        ClientHttpResponse response = execution.execute(request, body);
        InputStreamReader isr = new InputStreamReader(response.getBody(), StandardCharsets.UTF_8);
        String bodyString = new BufferedReader(isr).lines().collect(Collectors.joining("\n"));
        LOGGER.info("Response body: {}", bodyString);
        
        return response;
	}
}
