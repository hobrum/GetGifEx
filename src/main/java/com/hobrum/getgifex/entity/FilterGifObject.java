package com.hobrum.getgifex.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterGifObject {

    @MockBean
    private GifObject[] data;

    public GifObject[] getData() {return data;}

}
