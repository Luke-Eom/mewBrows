package com.book.mew.userFeign.feign.google;

import com.book.mew.userFeign.config.FeignConfiguration;
import com.book.mew.userFeign.dto.GoogleRequestAccessToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "googleAuth", url="https://oauth2.googleapis.com", configuration = {FeignConfiguration.class})
public interface GoogleAuthApi {

    @PostMapping("/token")
    ResponseEntity<String> getAccessToken(@RequestBody GoogleRequestAccessToken requestDto);

}