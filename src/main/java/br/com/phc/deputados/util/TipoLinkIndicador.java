package br.com.phc.deputados.util;

public enum TipoLinkIndicador {

	SELF("self"),
	NEXT("next"),
	FIRST("first"),
	LAST("last");

	private String value;

	private TipoLinkIndicador(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return this.value;
	}

}
