package com.mitocode.exception;

import java.time.LocalDateTime;
//inmutable
public record CustomErrorResponse(
		LocalDateTime dateTime,
		String message,
		String path
		) {

}
