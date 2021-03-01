package com.hobrum.getgifex.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterGifObject {


    private GifObject[] data;

    public GifObject[] getData() {return data;}

}
