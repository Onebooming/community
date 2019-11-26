package com.onebooming.community.community.service;

import com.onebooming.community.community.dto.PaginationDTO;
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

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = questionMapper.count();

        if(totalCount % size == 0){
            totalPage = totalCount / size;
        }else {
            totalPage = totalCount / size + 1;
        }



        if(page < 1){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }


        paginationDTO.setPagination(totalPage, page);


        //10 * (i - 1)
        Integer offsize = size * (page -1);

        List<Question> questionList = questionMapper.list(offsize, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for(Question question : questionList){
            User user = userMapper.findById(question.getCreatorId());
            //set questionDTO
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);

            //添加到集合中
            questionDTOList.add(questionDTO);
        }
        //System.out.println(questionDTOList);
        paginationDTO.setQuestions(questionDTOList);


        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = questionMapper.countByUserId(userId);

        if(totalCount % size == 0){
            totalPage = totalCount / size;
        }else {
            totalPage = totalCount / size + 1;
        }



        if(page < 1){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }


        paginationDTO.setPagination(totalPage, page);


        //10 * (i - 1)
        Integer offsize = size * (page -1);

        List<Question> questionList = questionMapper.list2(userId,offsize, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for(Question question : questionList){
            User user = userMapper.findById(question.getCreatorId());
            //set questionDTO
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);

            //添加到集合中
            questionDTOList.add(questionDTO);
        }
        //System.out.println(questionDTOList);
        paginationDTO.setQuestions(questionDTOList);


        return paginationDTO;
    }
}
