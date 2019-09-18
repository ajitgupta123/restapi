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
public class NoRecordFoundException extends RuntimeException {

    private static final long serialVersionUID = -378047737167461504L;
 
    public NoRecordFoundException(String message)
    {
        super(message);
    }
       
}
