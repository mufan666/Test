package com.mufan.utils;

/**
 * Created by aswe on 2016/8/1.
 */
public class JsonHandlerException extends  Exception {
    public JsonHandlerException() {
        super();
    }

    public JsonHandlerException(String message) {
        super(message);
    }

    public JsonHandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonHandlerException(Throwable cause) {
        super(cause);
    }
}
