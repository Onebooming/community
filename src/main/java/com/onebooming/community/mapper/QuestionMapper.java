package com.onebooming.community.mapper;

import com.onebooming.community.model.Question;
import org.apache.ibatis.annotations.*;

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

    @Select("select * from question where id = #{id}")
    Question getById(@Param(value = "id") Integer id);

    @Update("update question set title = #{title}, description = #{description}, tag = #{tag}, gmt_modified= #{gmtModified} where id = #{id}")
    void updateQuestion(Question question);
}
