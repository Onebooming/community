package com.onebooming.community.exception;

/**
 * @apiNote
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("你找的问题不存在！"),
    QUESTION_UPDATE_FAILURE("问题更新失败");
    @Override
    public String getMessage(){
        return message;
    }
    private String message;
    CustomizeErrorCode(String message){
        this.message = message;
    }
}
