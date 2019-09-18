/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdeveloperblog.app.ws.exception;

/**
 *
 * @author sergeykargopolov
 */
public class CouldNotCreateRecordException extends RuntimeException{

    private static final long serialVersionUID = -7892323019390152512L;


    public CouldNotCreateRecordException(String message)
    {
        super(message);
    }
}
