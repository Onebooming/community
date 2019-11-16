package com.onebooming.community.community.controller;

import com.onebooming.community.community.dto.AccessTokenDTO;
import com.onebooming.community.community.dto.GithubUserDTO;
import com.onebooming.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Onebooming
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    /**
     * 利用配置文件统一配置变量
     */

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request)
    {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
//        accessTokenDTO.setRedirect_uri("http://localhost:8081/callback");
//        accessTokenDTO.setClient_id("972d720e36803a394fd6");
//        accessTokenDTO.setClient_secret("fbd0ebc1bcf7745fc4ad57c74383bb8ee94f8a60");
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setRedirect_uri(redirectUri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUserDTO githubUser = githubProvider.getUser(accessToken);
        if(githubUser != null){
            //登录成功，写cookie和session
            request.getSession().setAttribute("user", githubUser);
            return "redirect:/";
        }else {
            //登录失败，重新登录
            return "redirect:/";
        }
    }
}
