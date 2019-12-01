package com.onebooming.community.provider;

import com.alibaba.fastjson.JSON;
import com.onebooming.community.dto.AccessTokenDTO;
import com.onebooming.community.dto.GithubUserDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Onebooming
 * @apiNote
 */
@Component
public class GithubProvider {

    /**
     * 向GitHub指定的授权url发送请求，获得access_token
     * @param accessTokenDTO
     * @return
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            System.out.println(string);
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取Github用户信息（id，name，bio等信息）
     * @param accessToken
     * @return
     */
    public GithubUserDTO getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            /**
             * 特别重要的一个方法：将String类型的JSON对象自动解析为一个java类对象
             */
            GithubUserDTO githubUserDTO = JSON.parseObject(responseString,GithubUserDTO.class);
            return githubUserDTO;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
