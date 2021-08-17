package com.applaudostudios.interview.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "This movie cant be sold at this moment. Try again later")
public class MovieCantBeSoldException extends RuntimeException {
}
