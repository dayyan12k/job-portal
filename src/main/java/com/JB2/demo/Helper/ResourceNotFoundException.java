package com.JB2.demo.Helper;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
    public  ResourceNotFoundException(){
        super();
    }
}
