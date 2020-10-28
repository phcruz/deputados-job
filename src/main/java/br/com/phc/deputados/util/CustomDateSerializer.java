package br.com.phc.deputados.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomDateSerializer extends JsonSerializer<Date> {

	private static final String FORMATO_PADRAO_DATA_BR = "dd/MM/yyyy";
			
	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		try {
			DateFormat formatter = new SimpleDateFormat(FORMATO_PADRAO_DATA_BR);
			String s = formatter.format(value);
			gen.writeString(s);
		} catch (Exception e) {
			gen.writeString("");
		}
	}

}
