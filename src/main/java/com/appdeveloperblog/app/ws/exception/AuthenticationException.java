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
public class AuthenticationException extends RuntimeException {

    private static final long serialVersionUID = -1911545563540172808L;


    public AuthenticationException(String message) {
        super(message);
    }

}
