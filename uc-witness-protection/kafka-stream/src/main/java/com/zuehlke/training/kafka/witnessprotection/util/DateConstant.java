package com.zuehlke.training.kafka.witnessprotection.util;

import java.time.format.DateTimeFormatter;

public interface DateConstant {

	String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DateConstant.DATE_TIME_FORMAT);

}
