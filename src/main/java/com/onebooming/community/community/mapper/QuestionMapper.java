package com.onebooming.community.community.mapper;

import com.onebooming.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Onebooming
 */
@Component
@Mapper
public interface QuestionMapper {
    @Insert("insert into question " +
            "(title,description,gmt_create,gmt_modified,creator_id,tag) " +
            "values " +
            "(#{title},#{description},#{gmtCreate},#{gmtModify},#{creatorId},#{tag})")
    public  void addQuestion(Question question);

    @Select("select * from question")
    public List<Question> getAll();
}
