package com.onebooming.community.mapper;

import com.onebooming.community.model.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class QuestionMapperTest {
    @Autowired
    QuestionMapper questionMapper;

    @Test
    public void pageQuery(){
        List<Question> questionList = new ArrayList<>();
        questionList = questionMapper.list2(13,1,5);
        for (Question q: questionList){
            System.out.println(q);
        }
    }
}
