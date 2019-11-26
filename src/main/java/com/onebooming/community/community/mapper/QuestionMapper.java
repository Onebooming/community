package com.onebooming.community.community.mapper;

import com.onebooming.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    @Select("select * from question limit #{offsize}, #{size}")
    public List<Question> list(@Param(value = "offsize") Integer offsize, @Param(value = "size") Integer size);

    @Select("select count(1) from question")
    public Integer count();

    @Select("select * from question where creator_id = #{userId} limit #{offsize}, #{size}")
    List<Question> list2(@Param(value = "userId") Integer userId, @Param(value = "offsize") Integer offsize, @Param(value = "size") Integer size);

    @Select("select count(1) from question where creator_id = #{userId}")
    Integer countByUserId(@Param(value = "userId") Integer userId);
}
