package com.onebooming.community.community.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Onebooming
 */
@Component
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;//向前按钮
    private boolean showFirstPage;//第一页按钮
    private boolean showNext;//下一页按钮
    private boolean showEndPage;//显示最后一页
    private Integer currentPage;//当前页
    private List<Integer> pages = new ArrayList<Integer>();//总共显示的页面数
    private Integer totalPage;


    public void setPagination(Integer totalPage, Integer page) {

        this.totalPage = totalPage;


        this.currentPage = page;
        pages.add(page);
        for(int i = 1 ;i<=3; i++){
            if(page - i > 0){
                pages.add(0, page - i);
            }
            if(page + i <= totalPage){
                pages.add(page + i);
            }
        }

        //是否展示上一页
        if(page == 1){
            this.showPrevious = false;
        }else {
            this.showPrevious = true;
        }

        //是否展示下一页
        if(page == totalPage){
            this.showNext = false;
        }else {
            this.showNext = true;
        }

        //是否展示第一页
        if(pages.contains(1)){
            this.showFirstPage = false;
        }else {
            this.showFirstPage = true;
        }

        //是否展示最后一页
        if(pages.contains(totalPage)){
            this.showEndPage = false;
        }else {
            this.showEndPage = true;
        }
    }
}
