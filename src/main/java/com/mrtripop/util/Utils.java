package com.mrtripop.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

  private Utils() {}

  private static final ObjectMapper mapper = new ObjectMapper();

  public static String writeString(Object input) throws JsonProcessingException {
    return mapper.writeValueAsString(input);
  }

  public static <T> T readValue(String input, Class<T> type) throws JsonProcessingException {
    return mapper.readValue(input, type);
  }
}
