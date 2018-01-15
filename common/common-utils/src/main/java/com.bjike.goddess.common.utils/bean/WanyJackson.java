package com.bjike.goddess.common.utils.bean;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WanyJackson {
    public static <TAR> List<TAR> batman(String data,Class cls) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, cls);
        List<TAR> list = objectMapper.readValue(data, javaType);
        return list;
    }

    public static <TAR> TAR superman(String data, Class cls) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TAR tar = (TAR) objectMapper.readValue(data, cls);
        return tar;
    }
}
