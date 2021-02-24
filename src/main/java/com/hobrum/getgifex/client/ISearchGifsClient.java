package com.hobrum.getgifex.client;

import com.hobrum.getgifex.entity.FilterGifObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "giphy", url = "${giphyApiURL}")
public interface ISearchGifsClient {

    @RequestMapping(value = "/v1/gifs/search", params = {"api_key", "q"}, method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    FilterGifObject searchGif(@RequestParam(value = "api_key") String token, @RequestParam(value = "q") String searchingQuery);

}
