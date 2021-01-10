package co.ratethem.controller.rest_exception_handler.exception;

import java.net.URISyntaxException;

public class InvalidDomainNameException extends URISyntaxException {
    public InvalidDomainNameException(String input, String reason, int index) {
        super(input, reason, index);
    }
}
