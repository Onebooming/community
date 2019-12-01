package com.onebooming.community.dto;

import com.onebooming.community.model.User;
import lombok.Data;

/**
 * 用于根据问题查询对应用户信息
 */

@Data
public class QuestionDTO {
    private Integer id;
    private String title;//标题
    private String description;//描述
    private Long gmtCreate;//创建时间
    private Long gmtModify;//修改时间
    private Integer creatorId;//创建者id
    private Integer commentSum;//评论数
    private Integer browseSum;//浏览数
    private Integer thumbsupSum;//点赞数
    private String tag;//标签
    private User user;
}
