package com.onebooming.community.community.controller;

import com.onebooming.community.community.dto.PaginationDTO;
import com.onebooming.community.community.mapper.UserMapper;
import com.onebooming.community.community.model.Question;
import com.onebooming.community.community.model.User;
import com.onebooming.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Onebooming
 * @apiNote
 */
@Controller
public class ProfileController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "10") Integer size){
        User user = null;
        //通过request获取网页中的cookie数组
        Cookie[] cookies = request.getCookies();
        //便利cookies，查询name为token的cookie，并取其值
        //对cookies做一个非空判断
        if(cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    //根据token查询数据库是否存在对应的User对象
                    user = userMapper.findByToken(token);
                    System.out.println(user);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }

                    break;
                }
            }
        }
        if (user == null){
            return "redirect/";
        }


        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");

        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }


        PaginationDTO paginationDTO = questionService.list(user.getId(),page,size);
        model.addAttribute("pagination",paginationDTO);
        return "profile";
    }
}
