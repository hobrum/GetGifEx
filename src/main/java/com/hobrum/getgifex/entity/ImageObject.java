package com.hobrum.getgifex.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageObject {


    private Map<String, String> original;

    public Map<String,String> getOriginal() {
        return original;
    }

}
