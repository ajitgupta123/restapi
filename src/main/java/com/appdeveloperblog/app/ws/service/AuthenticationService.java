/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdeveloperblog.app.ws.service;

import com.appdeveloperblog.app.ws.exception.AuthenticationException;
import com.appdeveloperblog.app.ws.shared.dto.UserDTO;

/**
 *
 * @author gts
 */
public interface AuthenticationService {
    UserDTO authenticate(String username, String password) throws AuthenticationException;
    String issueAccessToken(UserDTO userProfile) throws AuthenticationException;
    
}
