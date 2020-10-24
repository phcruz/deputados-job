package br.com.phc.deputados.exception;

import java.io.Serializable;
import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

	public StandardError(HttpStatus httpStatus, String message, String path) {
		super();
		this.status = httpStatus.value();
		this.error = httpStatus.name();
		this.timestamp = new Date().getTime();
		this.message = message;
		this.path = path;
	}
}