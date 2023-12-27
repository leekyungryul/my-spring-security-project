package com.example.myspringsecurityproject.login.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.example.myspringsecurityproject.common.constant.MyConstant;
import com.example.myspringsecurityproject.common.exception.MyException;
import com.example.myspringsecurityproject.login.domain.KakaoOAuthToken;
import com.example.myspringsecurityproject.login.domain.KakaoUserInfo;
import com.example.myspringsecurityproject.login.domain.NaverUserInfo;
import com.example.myspringsecurityproject.login.repository.LoginRepository;
import com.example.myspringsecurityproject.login.service.LoginService;
import com.example.myspringsecurityproject.manage.user.domain.UserVO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    LoginRepository repository;

    @Autowired
    RestTemplate restTemplate;

    @Value("${oauth2.kakao.api-key}")
    private String kakaoApiKey;

    @Value("${oauth2.kakao.redirect-uri}")
    private String kakaoRedirectUri;

    @Value("${oauth2.kakao.token-uri}")
    private String kakaoTokenUri;

    @Value("${oauth2.kakao.user-info-uri}")
    private String kakaoUserInfoUri;

    @Value("${oauth2.naver.api-key}")
    private String naverApiKey;

    @Value("${oauth2.naver.client-secret}")
    private String naverClientSecret;

    @Value("${oauth2.naver.redirect-uri}")
    private String naverRedirectUri;

    @Value("${oauth2.naver.token-uri}")
    private String naverTokenUri;

    @Value("${oauth2.naver.user-info-uri}")
    private String naverUserInfoUri;

    @Value("${oauth2.google.api-key}")
    private String googleApiKey;

    @Value("${oauth2.google.client-secret}")
    private String googleClientSecret;

    @Value("${oauth2.google.token-uri}")
    private String googleTokenUri;

    @Value("${oauth2.google.user-info-uri}")
    private String googleUserInfoUri;

    @Value("${oauth2.google.redirect-uri}")
    private String googleRedirectUri;

    @Override
    public UserVO findByUsername(Map<String, Object> params) throws MyException {

        if (logger.isDebugEnabled()) {
            logger.debug(MyConstant.LOG_PARAM, this.getClass().getName(), "findByUsername", params);
        }

        return repository.selectUser(params);

    }

    /**
     * 카카오콜백 정보로 사용자를 조회한다.
     * @param code
     * @return
     * @throws MyException
     */
    @Override
    public Map<String, Object> findByKakaoId(String code) throws MyException {

        if (logger.isDebugEnabled()) {
            logger.debug(MyConstant.LOG_PARAM, this.getClass().getName(), "findByKakaoId", code);
        }

        restTemplate = new RestTemplate();
        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        // HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoApiKey);
        params.add("redirect_uri", kakaoRedirectUri);
        params.add("code", code);
        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
        // Http 요청하기 - Post 방식으로 - 그리고 response 변수의 응답 받음.
        KakaoOAuthToken kakaoTokenResponse = restTemplate.postForObject(kakaoTokenUri, kakaoTokenRequest, KakaoOAuthToken.class);

        restTemplate = new RestTemplate();
        // HttpHeader 오브젝트 생성
        headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + (kakaoTokenResponse != null ? kakaoTokenResponse.getAccess_token() : null));
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);
        KakaoUserInfo kakaoProfileResponse = restTemplate.postForObject(kakaoUserInfoUri, kakaoProfileRequest, KakaoUserInfo.class);

        Map<String, Object> params2 = new HashMap<>();
        params2.put("provider", "kakao");
        assert kakaoProfileResponse != null;
        params2.put("providerUserId", kakaoProfileResponse.getId());

        Map<String, Object> result = new HashMap<>();
        result.put("user", repository.findByOAuth2Id(params2));
        result.put("oAuth2UserInfo", kakaoProfileResponse);

        return result;

    }

    /**
     * 네이버콜백 정보로 사용자를 조회한다.
     * @param code
     * @return
     * @throws MyException
     */
    @Override
    public Map<String, Object> findByNaverId(String code) throws MyException {

        if (logger.isDebugEnabled()) {
            logger.debug(MyConstant.LOG_PARAM, this.getClass().getName(), "findByNaverId", code);
        }

        restTemplate = new RestTemplate();
        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        // HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", naverApiKey);
        params.add("client_secret", naverClientSecret);
        params.add("code", code);
        params.add("state", "1234");
        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> naverTokenRequest = new HttpEntity<>(params, headers);
        // Http 요청하기 - Post 방식으로 - 그리고 response 변수의 응답 받음.
        KakaoOAuthToken naverTokenResponse = restTemplate.postForObject(naverTokenUri, naverTokenRequest, KakaoOAuthToken.class);
        logger.info("naverTokenResponse: {}", naverTokenResponse);

        restTemplate = new RestTemplate();
        // HttpHeader 오브젝트 생성
        headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + (naverTokenResponse != null ? naverTokenResponse.getAccess_token() : null));
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> naverProfileRequest = new HttpEntity<>(headers);
        ResponseEntity<Map> naverProfileResponse = restTemplate.exchange(naverUserInfoUri, HttpMethod.GET, naverProfileRequest, Map.class);
        logger.info("naverProfileResponse: {}", naverProfileResponse);

        ObjectMapper objectMapper = new ObjectMapper();
        NaverUserInfo naverUserInfoResponse = objectMapper.convertValue(naverProfileResponse.getBody().get("response"), NaverUserInfo.class);

        Map<String, Object> params2 = new HashMap<>();
        params2.put("provider", "naver");
        params2.put("providerUserId", naverUserInfoResponse.getId());

        Map<String, Object> result = new HashMap<>();
        result.put("user", repository.findByOAuth2Id(params2));
        result.put("oAuth2UserInfo", naverUserInfoResponse);

        return result;
    }

    @Override
    public Map<String, Object> findByGoogleId(String code) throws MyException {

        if (logger.isDebugEnabled()) {
            logger.debug(MyConstant.LOG_PARAM, this.getClass().getName(), "findByGoogleId", code);
        }

        restTemplate = new RestTemplate();
        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        // HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", googleApiKey);
        params.add("client_secret", googleClientSecret);
        params.add("code", code);
        params.add("redirect_uri", googleRedirectUri);
        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> googleTokenRequest = new HttpEntity<>(params, headers);
        // Http 요청하기 - Post 방식으로 - 그리고 response 변수의 응답 받음.
        ResponseEntity<String> exchange = restTemplate.postForEntity(googleTokenUri, googleTokenRequest, String.class);
        logger.info("exchange: {}", exchange);
//        String googleTokenResponse = restTemplate.postForObject(googleTokenUri, googleTokenRequest, String.class);
//        logger.info("googleTokenResponse: {}", googleTokenResponse);



        return null;

    }
}
