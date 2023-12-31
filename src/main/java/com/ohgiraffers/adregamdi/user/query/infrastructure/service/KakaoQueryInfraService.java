package com.ohgiraffers.adregamdi.user.query.infrastructure.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ohgiraffers.adregamdi.common.annotation.InfraService;
import com.ohgiraffers.adregamdi.user.command.application.dto.KakaoUserDTO;
import com.ohgiraffers.adregamdi.user.command.application.dto.TokenDTO;
import com.ohgiraffers.adregamdi.user.command.domain.exception.UserException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@InfraService
public class KakaoQueryInfraService {

    // 카카오 액세스 토큰 발급
    public TokenDTO getKakaoAccessToken(String code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";
        TokenDTO token = null;
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            String sb = "grant_type=authorization_code" +
                    "&client_id=dd52d3a258bf3ccbc37de832dfecd0d4" + // REST API 키
                    "&redirect_uri=http://localhost:9090/oauth/kakao/login" + // Redirect URI
                    "&code=" + code;
            System.out.println(sb);
            bw.write(sb);
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            StringBuilder result = new StringBuilder();

            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            System.out.println("response body : " + result);

            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonElement element = JsonParser.parseString(result.toString());

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
            token = new TokenDTO(access_Token, refresh_Token);
            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }

    // 카카오 유저 정보 조회
    public KakaoUserDTO getKakaoUserInfo(TokenDTO token) {
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        KakaoUserDTO userInfo = null;

        //access_token을 이용하여 사용자 정보 조회
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            System.out.println("token = " + token.getAccess_Token());
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token.getAccess_Token()); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder result = new StringBuilder();

            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            System.out.println("response body : " + result);

            //Gson 라이브러리로 JSON파싱
            JsonElement element = JsonParser.parseString(result.toString());

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            UserException userException = new UserException();
            String id = element.getAsJsonObject().get("id").getAsString();
            String profile = properties.getAsJsonObject().get("profile_image").getAsString();
            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            String email = userException.solveNullPointerException("email", kakao_account);
            String age = userException.solveNullPointerException("age_range", kakao_account);
            String gender = userException.solveNullPointerException("gender", kakao_account);

            userInfo = new KakaoUserDTO();
            userInfo.setKakaoId(id);
            userInfo.setKakaoProfileImage(profile);
            userInfo.setKakaoNickName(nickname);
            userInfo.setEmail(email);
            userInfo.setAge(age);
            userInfo.setGender(gender);
            userInfo.setAccess_Token(token.getAccess_Token());
            userInfo.setRefresh_Token(token.getRefresh_Token());

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

}
