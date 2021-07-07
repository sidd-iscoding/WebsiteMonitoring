package com.example.isthesiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {
    private final String SITE_IS_UP="site is up!";
    private final String SITE_IS_DOWN="site is DOWN!";
    private final String INCORRECT_URL="The url is incorrect!";
    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url){  //ping and identify whether a website is up/down
        String returnMessage="";
        try {
            URL urlObj =new URL(url);
            HttpURLConnection conn= (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCodeCategory=conn.getResponseCode() / 100 ;
            System.out.println(conn.getResponseCode());
            if(responseCodeCategory == 2 || responseCodeCategory == 3 ){
                 returnMessage=SITE_IS_UP + " " + conn.getResponseCode();
            }else{
                 returnMessage=SITE_IS_DOWN + " " + conn.getResponseCode();
            }
        } catch (MalformedURLException e) {
             returnMessage=INCORRECT_URL;
        } catch (IOException e) {
             returnMessage=SITE_IS_DOWN  ;
        }
        return returnMessage;
    }
}
