package com.rocketseat.planner.exceptions;

import org.apache.catalina.User;

public class ParticipantsNotFoundException extends RuntimeException {


    public ParticipantsNotFoundException() {
        super ("Não encontrados participantes");
    }

    public ParticipantsNotFoundException(String message) {
        super(message);
    }
}