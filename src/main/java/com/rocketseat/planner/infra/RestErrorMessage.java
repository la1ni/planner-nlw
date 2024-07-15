package com.rocketseat.planner.infra;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class RestErrorMessage {
    private HttpStatus status;
    private String message;
}
