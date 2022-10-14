package com.yuhang.controller.util;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {

    @ExceptionHandler
    public R doOtherException(Exception e){
        e.printStackTrace();
        return new R("服务器错误！");
    }
}
