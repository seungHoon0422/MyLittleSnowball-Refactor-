package com.app.FitPL.oauth;


import com.app.FitPL.oauth.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {

    private final KakaoService kakaoService;

    @GetMapping("/kakao/{code}")
    public ResponseEntity<?> kakaoLogin(@PathVariable(value = "code") String code) throws IOException {
        String response = kakaoService.getToken(code);
        Map<String, Object> userInfo = kakaoService.getUserInfo(response);
        return new ResponseEntity<Map<String, Object>>(userInfo, HttpStatus.ACCEPTED);
    }

    @GetMapping("/kakao/user/{access_token}")
    public ResponseEntity<?> kakaoUserInfo(@PathVariable(value = "access_token") String accessToken) throws IOException {
        Map<String, Object> response = kakaoService.getUserInfo(accessToken);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
    }


}