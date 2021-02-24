package com.hobrum.getgifex.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "showgif", url = "${giphyMediaURL}", qualifier = "showgif")
public interface IShowGifClient {

    @RequestMapping(value = "{path}", method = RequestMethod.GET)
    byte[] showGif(@PathVariable String path);


}
