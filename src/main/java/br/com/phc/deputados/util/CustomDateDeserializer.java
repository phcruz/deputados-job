package br.com.phc.deputados.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomDateDeserializer extends JsonDeserializer<Date> {

	private static final String FORMATO_PADRAO_DATA_API = "yyyy-MM-dd";
	
	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMATO_PADRAO_DATA_API);
		try {
			return formatter.parse(p.getText());
		} catch (ParseException e) {
			return null;
		}
	}

}
