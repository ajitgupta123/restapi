/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdeveloperblog.app.ws.io.dao;

import com.appdeveloperblog.app.ws.shared.dto.UserDTO;

/**
 *
 * @author gts
 */
public interface DAO {
    void openConnection();
    UserDTO getUserByUserName(String userName);
    UserDTO getUser(String id); 
    void updateUser(UserDTO userProfile);
    UserDTO saveUser(UserDTO userDTO);
    void closeConnection();
}
