package com.onebooming.community.community.service;

import com.onebooming.community.community.dto.QuestionDTO;
import com.onebooming.community.community.mapper.QuestionMapper;
import com.onebooming.community.community.mapper.UserMapper;
import com.onebooming.community.community.model.Question;
import com.onebooming.community.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Onebooming
 */

@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;

    public List<QuestionDTO> list(){
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questionList = questionMapper.getAll();
        for(Question question : questionList){

            User user = userMapper.findById(question.getCreatorId());

            QuestionDTO questionDTO = new QuestionDTO();

            /**
             * 以下代码相当于
             * questionDTO.setId(question.getId());
             * questionDTO.setTitle(question.getTitle());
             * questionDTO.setDescription(question.getDescription());
             * questionDTO.setGmtCreate(question.getGmtCreate());
             * questionDTO.setGmtModify(question.getGmtModify());
             * questionDTO.setCreatorId(question.getCreatorId());
             * questionDTO.setCommentSum(question.getCommentSum());
             * questionDTO.setBrowseSum(question.getBrowseSum());
             * questionDTO.setThumbsupSum(question.getThumbsupSum());
             * questionDTO.setTag(question.getTag());
             * questionDTO.setUser(user);
             */
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);

            questionDTOList.add(questionDTO);
        }
        System.out.println(questionDTOList);

        return questionDTOList;

    }
}
