package com.huaxin.exception;

public class BeanException extends RuntimeException{
    public BeanException(){
        super();
    }

    public BeanException(String message){
        super(message);
    }
}
