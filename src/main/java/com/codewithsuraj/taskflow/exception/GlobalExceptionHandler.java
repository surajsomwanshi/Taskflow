package com.codewithsuraj.taskflow.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /** 404 — a specific, expected failure. */
    @ExceptionHandler(TaskNotFoundException.class)
    public ProblemDetail handleNotFound(TaskNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setTitle("Task not found");
        problem.setProperty("timestamp", Instant.now());
        return problem;   // Spring returns HTTP 404, matching the detail's status
    }

    /** 400 — validation failures, with a field-by-field breakdown. */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Validation failed");
        Map<String, String> fields = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e ->
                fields.put(e.getField(), e.getDefaultMessage()));
        problem.setProperty("fields", fields);
        return problem;
    }

    /** 500 — the catch-all. Log the real cause; never leak it to the client. */
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleUnexpected(Exception ex) {
        log.error("Unexpected error", ex);   // full stack trace in the logs
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        problem.setTitle("Internal server error");
        return problem;   // generic message to the client — no internals leaked
    }
}
