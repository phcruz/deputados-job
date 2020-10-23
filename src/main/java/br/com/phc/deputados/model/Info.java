package br.com.phc.deputados.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Info implements Serializable {

	private static final long serialVersionUID = 1L;

	private String rel;
	private String href;

}
