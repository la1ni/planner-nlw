package com.rocketseat.planner.infra;

import com.rocketseat.planner.exceptions.InvalidDatesException;
import com.rocketseat.planner.exceptions.ParticipantsNotFoundException;
import com.rocketseat.planner.exceptions.TripNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidDatesException.class)
    private ResponseEntity<RestErrorMessage> invalidDatesExceptionHandler (InvalidDatesException exception) {
        RestErrorMessage response = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ParticipantsNotFoundException.class)
    private ResponseEntity<RestErrorMessage> participantsNotFoundExceptionHandler (ParticipantsNotFoundException exception) {
        RestErrorMessage response = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(TripNotFoundException.class)
    private ResponseEntity<RestErrorMessage> tripNotFoundExceptionHandler (TripNotFoundException exception) {
        RestErrorMessage response = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<RestErrorMessage> runtimeExceptionHandler (RuntimeException exception) {
        RestErrorMessage response = new RestErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }



}
