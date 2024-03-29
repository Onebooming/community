package com.onebooming.community.interceptor;

import com.onebooming.community.mapper.UserMapper;
import com.onebooming.community.model.User;
import com.onebooming.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Onebooming
 */
@Service
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //通过request获取网页中的cookie数组
        Cookie[] cookies = request.getCookies();
        //遍历cookies，查询name为token的cookie，并取其值
        //对cookies做一个非空判断
        if(cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    //根据token查询数据库是否存在对应的User对象
                    UserExample userExample = new UserExample();
                    userExample.createCriteria().andTokenEqualTo(token);
                    List<User> users = userMapper.selectByExample(userExample);
                    System.out.println(users.get(0));
                    if (users.size() !=  0) {
                        request.getSession().setAttribute("user", users.get(0));
                    }

                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
