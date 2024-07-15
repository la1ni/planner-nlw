package com.rocketseat.planner.exceptions;

public class InvalidDatesException extends RuntimeException {


    public InvalidDatesException() {
        super ("Datas inválidas");
    }

    public InvalidDatesException(String message) {
        super(message);
    }
}
