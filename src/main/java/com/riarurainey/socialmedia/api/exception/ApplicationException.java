package com.riarurainey.socialmedia.api.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@RequiredArgsConstructor
public class ApplicationException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;
}
