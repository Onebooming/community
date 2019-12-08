package com.onebooming.community.mapper;

import com.onebooming.community.model.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class QuestionMapperTest {
    @Autowired
    QuestionMapper questionMapper;

    @Test
    public void pageQuery(){
        List<Question> questionList = new ArrayList<>();
        Map<String,Object> params = new HashMap<>();
        params.put("userId",13);
        params.put("offsize",1);
        params.put("size",5);
        questionList = questionMapper.pageListByUser(params);
        for (Question q: questionList){
            System.out.println(q);
        }
    }
}
