package com.enigma.livecodeecommerce.exception;

public class DataEmptyException extends RuntimeException{
    public DataEmptyException(){
        super("Data is empty");
    }
    public DataEmptyException(String message){
        super(message);
    }
}
