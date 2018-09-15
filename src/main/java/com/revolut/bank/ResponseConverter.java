package com.revolut.bank;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.common.HttpStatus;
import com.linecorp.armeria.common.MediaType;
import com.linecorp.armeria.server.ServiceRequestContext;
import com.linecorp.armeria.server.annotation.ResponseConverterFunction;
import com.revolut.bank.model.BankEntity;

import javax.annotation.Nullable;

public class ResponseConverter implements ResponseConverterFunction {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public HttpResponse convertResponse(ServiceRequestContext ctx, @Nullable Object result) throws Exception {
        if (result instanceof BankEntity) {
            return HttpResponse.of(HttpStatus.OK,
                    MediaType.PLAIN_TEXT_UTF_8,
                    objectMapper.writeValueAsString(result));
        }

        // To the next response converter.
        return ResponseConverterFunction.fallthrough();
    }
}
