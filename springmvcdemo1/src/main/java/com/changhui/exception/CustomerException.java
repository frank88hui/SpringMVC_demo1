package com.changhui.exception;


public class CustomerException extends Exception {

    private static final long serialVersionUID = -2161183854223776003L;
    private String exceptMsg;

    public CustomerException(String exceptMsg){
        this.exceptMsg = exceptMsg;
    }

    public String getExceptMsg() {
        return exceptMsg;
    }

    public void setExceptMsg(String exceptMsg) {
        this.exceptMsg = exceptMsg;
    }

}
