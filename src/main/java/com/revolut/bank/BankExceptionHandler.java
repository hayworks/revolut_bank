package com.revolut.bank;

import com.linecorp.armeria.common.*;
import com.linecorp.armeria.server.annotation.ExceptionHandlerFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankExceptionHandler implements ExceptionHandlerFunction {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankExceptionHandler.class);

    @Override
    public HttpResponse handleException(RequestContext ctx, HttpRequest req, Throwable cause) {
        LOGGER.error(cause.getMessage(), cause);
        return HttpResponse.of(HttpStatus.BAD_REQUEST, MediaType.PLAIN_TEXT_UTF_8, cause.getMessage());
    }
}
