package com.hobrum.getgifex.service;

import com.hobrum.getgifex.client.ISearchGifsClient;
import com.hobrum.getgifex.client.IShowGifClient;
import com.hobrum.getgifex.entity.FilterGifObject;
import com.hobrum.getgifex.entity.GifObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Random;


@Service
public class GiphyService {


    @Value("${giphyApiToken}")
    private String giphyApiToken;


    private ISearchGifsClient searchingGifClient;


    private ApplicationContext applicationContext;

    public GiphyService(ISearchGifsClient searchingGifClient, ApplicationContext applicationContext) {
        this.searchingGifClient = searchingGifClient;
        this.applicationContext = applicationContext;
    }


    public byte[] getRandomGif(boolean isEchangeRateIsHigher) throws IOException {

        String searchingWorld = isEchangeRateIsHigher ? "rich" : "broke";
        FilterGifObject filterGifObject = searchingGifClient.searchGif(giphyApiToken, searchingWorld);
        GifObject[] gifObjects = filterGifObject.getData();
        Random random = new Random();
        Map<String, String> selectedOriginalImage = gifObjects[random.nextInt(((gifObjects.length - 1) + 1))].getImages().getOriginal();
        URL urlOriginal = new URL(selectedOriginalImage.get("url"));
        String path = urlOriginal.getPath();
        IShowGifClient showGifClient = (IShowGifClient) applicationContext.getBean("showgif");
        return showGifClient.showGif(path);
    }


}


