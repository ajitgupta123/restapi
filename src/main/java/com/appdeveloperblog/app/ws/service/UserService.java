/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdeveloperblog.app.ws.service;

import com.appdeveloperblog.app.ws.shared.dto.UserDTO;

/**
 *
 * @author gts
 */
public interface UserService {
    public UserDTO createUser(UserDTO request);
    public UserDTO getUser(String id);
    public UserDTO getUserByUserName(String userName);
}
