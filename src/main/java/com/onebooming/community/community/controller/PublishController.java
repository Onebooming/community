package com.onebooming.community.community.controller;

import com.onebooming.community.community.mapper.QuestionMapper;
import com.onebooming.community.community.model.Question;
import com.onebooming.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Onebooming
 * @apiNote 关于publish界面的控制
 */
@Controller
public class PublishController {

    @Autowired
    QuestionMapper questionMapper;


    /**
     * Get--渲染页面
     * Post--执行请求
     * @return
     */

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "tag",required = false) String tag,
            HttpServletRequest request,
            Model model){

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);


        if(title == null || title == ""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description == null || description == ""){
            model.addAttribute("error","描述不能为空");
            return "publish";
        }
        if(tag == null || tag == ""){
            model.addAttribute("error","便签不能为空");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        //创建Question对象，并将前段传过来的参数赋值给对象属性
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        //question.setCreatorId(Integer.parseInt(user.getAccountId()));
        question.setCreatorId(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModify(System.currentTimeMillis());
        //添加Question对象进数据库
        questionMapper.addQuestion(question);
        return "redirect:/";
    }
}