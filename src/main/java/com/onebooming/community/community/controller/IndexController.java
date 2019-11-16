package com.onebooming.community.community.controller;

import com.alibaba.fastjson.JSON;
import com.onebooming.community.community.mapper.UserMapper;
import com.onebooming.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Onebooming
 * @Date 2019-11-15
 */
@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        /**
         * 这一段的业务逻辑是说，如果用户登录过，则就会在网页中生成一个名为token的cookie（自定义的）
         * 当刷新页面时，我们先从"/"这个路径的页面中请求查询cookie，查到token，并访问数据库表，看看有没有
         * 直接对应的User对象，如果有，则取出User信息，不需要用户重复去登录
         */

        //通过request获取网页中的cookie数组
        Cookie[] cookies = request.getCookies();
        //便利cookies，查询name为token的cookie，并取其值
        //对cookies做一个非空判断
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName());
            if ("token".equals(cookie.getName())) {
                String token = cookie.getValue();
                //根据token查询数据库是否存在对应的User对象
                User user = userMapper.findByToken(token);
                System.out.println(user);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }

                break;
            }
        }


        return "index";
    }
}
