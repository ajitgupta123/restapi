/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdeveloperblog.app.ws.exception;

/**
 *
 * @author gts
 */
public class MissingRequiredFieldException extends RuntimeException {

    private static final long serialVersionUID = -8399174078978731369L;

    public MissingRequiredFieldException(String message) {
        super(message);
    }
    
}
