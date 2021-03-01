package com.hobrum.getgifex.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@JsonIgnoreProperties(ignoreUnknown = true)
public class GifObject {


    private ImageObject images;

    public ImageObject getImages() {
        return images;
    }


}
