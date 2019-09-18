/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdeveloperblog.app.ws.service;

import com.appdeveloperblog.app.ws.exception.CouldNotCreateRecordException;
import com.appdeveloperblog.app.ws.io.dao.DAO;
import com.appdeveloperblog.app.ws.io.dao.impl.MySQLDAO;
import com.appdeveloperblog.app.ws.shared.dto.UserDTO;
import com.appdeveloperblog.app.ws.ui.model.response.ErrorMessages;
import com.appdeveloperblog.app.ws.utils.UserProfileUtils;
import com.appdeveloperblog.app.ws.exception.NoRecordFoundException;

/**
 *
 * @author gts
 */
public class UserServiceImpl implements UserService{
    DAO database;
    UserProfileUtils userProfileUtils = new UserProfileUtils();

    public UserServiceImpl() {
        database = new MySQLDAO();
    }
    
    @Override
    public UserDTO createUser(UserDTO user) {
        UserDTO returnObj = new UserDTO();
        // Validate the require fields
        userProfileUtils.validateRequiredFields(user);
        
        // Check if user already exists
        UserDTO existingUser = this.getUserByUserName(user.getEmail());
        
        if (existingUser != null) {
            throw new CouldNotCreateRecordException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }
        
        // Create an entity object
        
        // Generate secure public user id
        String user_id = userProfileUtils.generateRandomString(30);
        user.setUserId(user_id);
        
        // Generate user id
        
        
        // Generate secure password
        String salt = userProfileUtils.getSalt(30);
        String securePassword = userProfileUtils.generateSecurePassword(user.getPassword(), salt);
        user.setSalt(salt);
        user.setEncryptedPassword(securePassword);
        
        // Record data into database
        returnObj = this.saveUser(user);
        
        // Return back the user profile
        
        return returnObj;
    }
    
    @Override
    public UserDTO getUser(String id) {
        UserDTO returnValue = null;

        // Connect to database 
        try {
            this.database.openConnection();
            returnValue = this.database.getUser(id);
        } catch(Exception ex){
            ex.printStackTrace();
            throw new NoRecordFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }finally {
            this.database.closeConnection();
        }
        
        return returnValue;
    }
    
    public UserDTO getUserByUserName(String userName) {
        UserDTO userDto = null;

        if (userName == null || userName.isEmpty()) {
            return userDto;
        }

        // Connect to database 
        try {
            this.database.openConnection();
            userDto = this.database.getUserByUserName(userName);
        } finally {
            this.database.closeConnection();
        }

        return userDto;
    }
    
    public UserDTO saveUser(UserDTO userDTO) {
        UserDTO userDto = null;


        // Connect to database 
        try {
            this.database.openConnection();
            userDto = this.database.saveUser(userDTO);
        } finally {
            this.database.closeConnection();
        }

        return userDto;
    }


    
}
