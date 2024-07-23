package com.cody.common.utils;

import com.cody.common.exception.server.ServerException;
import com.cody.common.exception.server.ServerExceptionCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> String objectToJson(T object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new ServerException(ServerExceptionCode.JSON_PARSER_EXCEPTION);
        }
    }

}
