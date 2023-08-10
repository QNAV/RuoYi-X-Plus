package com.ruoyi.wx.exception;


public class WxException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public WxException(String msg) {
        super(msg);
    }

}
