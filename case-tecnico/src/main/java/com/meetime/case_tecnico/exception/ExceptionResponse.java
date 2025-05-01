package com.meetime.case_tecnico.exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String mensage, String details) {}
