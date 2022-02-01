package com.sbrf.reboot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbrf.reboot.dto.Request;
import com.sbrf.reboot.dto.Response;

public class JSONUtils {
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    public static String toJSON(Response response) throws JsonProcessingException {
        return objectMapper.writeValueAsString(response);
    }

    public static String toJSON(Request request) throws JsonProcessingException {
        return objectMapper.writeValueAsString(request);
    }

    public static Response JSONtoResponse(String JSONResponse) throws JsonProcessingException {
        return objectMapper.readValue(JSONResponse, Response.class);
    }

    public static Request JSONtoRequest(String JSONRequest) throws JsonProcessingException {
        return objectMapper.readValue(JSONRequest, Request.class);
    }
}
