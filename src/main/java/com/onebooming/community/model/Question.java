package com.onebooming.community.model;


import lombok.Data;

/**
 * @author Onebooming
 */
@Data
public class Question {
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

}
