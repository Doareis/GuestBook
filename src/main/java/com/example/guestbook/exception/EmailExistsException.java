package com.example.guestbook.exception;

import static java.lang.String.format;

public class EmailExistsException extends Throwable {

    public EmailExistsException(String pattern, Object ... params) {
        super(format(pattern, params));
    }
}
