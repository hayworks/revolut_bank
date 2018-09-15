package com.revolut.bank;

import com.linecorp.armeria.common.*;
import com.linecorp.armeria.server.annotation.ExceptionHandlerFunction;

public class BankExceptionHandler implements ExceptionHandlerFunction {
    @Override
    public HttpResponse handleException(RequestContext ctx, HttpRequest req, Throwable cause) {
        return HttpResponse.of(HttpStatus.BAD_REQUEST, MediaType.PLAIN_TEXT_UTF_8, cause.getMessage());
    }
}
