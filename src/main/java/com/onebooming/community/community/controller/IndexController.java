package com.onebooming.community.community.controller;

import com.onebooming.community.community.dto.PaginationDTO;

import com.onebooming.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author Onebooming
 * @Date 2019-11-15
 */
@Controller
public class IndexController {
    @Autowired
    QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "10") Integer size) {
        /**
         * 这一段的业务逻辑是说，如果用户登录过，则就会在网页中生成一个名为token的cookie（自定义的）
         * 当刷新页面时，我们先从"/"这个路径的页面中请求查询cookie，查到token，并访问数据库表，看看有没有
         * 直接对应的User对象，如果有，则取出User信息，不需要用户重复去登录
         */

        //获取数据库中Question记录，并将数据传给前段
        //List<QuestionDTO> questionList = questionService.list(page,size);
        PaginationDTO pagination = questionService.list(page,size);
        //model.addAttribute("questions",questionList);
        model.addAttribute("pagination", pagination);

        return "index";
    }
}
