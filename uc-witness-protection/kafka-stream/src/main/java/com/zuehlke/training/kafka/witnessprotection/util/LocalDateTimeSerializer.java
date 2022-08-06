package com.zuehlke.training.kafka.witnessprotection.util;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

	private static final long serialVersionUID = 1L;

	public LocalDateTimeSerializer() {
		this(null);
	}

	public LocalDateTimeSerializer(Class<LocalDateTime> t) {
		super(t);
	}

	@Override
	public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider arg2) throws IOException {
		gen.writeString(DateConstant.DATE_TIME_FORMATTER.format(value));
	}
}
