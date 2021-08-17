package com.applaudostudios.interview.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "element does not exist")
public class ElementNotFoundException extends RuntimeException {

}
