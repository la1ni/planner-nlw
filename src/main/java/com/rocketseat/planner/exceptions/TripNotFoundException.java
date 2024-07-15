package com.rocketseat.planner.exceptions;

public class TripNotFoundException extends RuntimeException {


    public TripNotFoundException() {
            super ("Viagem não encontrada");
        }

    public TripNotFoundException(String message) {
            super(message);
        }
    }
