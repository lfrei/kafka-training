package com.zuehlke.training.kafka.witnessprotection.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

	private static final long serialVersionUID = 1L;

	public LocalDateTimeDeserializer() {
		super(LocalDate.class);
	}

	@Override
	public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
		return LocalDateTime.parse(parser.readValueAs(String.class), DateConstant.DATE_TIME_FORMATTER);
	}

}
