/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdeveloperblog.app.ws.ui.entrypoints;

import com.appdeveloperblog.app.ws.service.UserService;
import com.appdeveloperblog.app.ws.service.UserServiceImpl;
import com.appdeveloperblog.app.ws.shared.dto.UserDTO;
import com.appdeveloperblog.app.ws.ui.model.request.CreateUserRequestModel;
import com.appdeveloperblog.app.ws.ui.model.response.UserProfileRest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author gts
 */
@Path("/users")
public class UsersEntryPoint {
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON,  MediaType.APPLICATION_XML })
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public UserProfileRest createUser(CreateUserRequestModel requestObject){
        UserProfileRest retValue = new UserProfileRest();
        
        //Prepare userDTO
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(requestObject, userDTO);
        
        // Call Service method
        UserService userService = new UserServiceImpl();
        UserDTO newUserDTO = userService.createUser(userDTO);
        
        BeanUtils.copyProperties(newUserDTO, retValue);
        
        return retValue;
    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public UserProfileRest getUserProfile(@PathParam("id") String id) {
        UserProfileRest userProfileRest = null;
        
        UserService userService = new UserServiceImpl();
        UserDTO newUserDTO = userService.getUser(id);
        
        userProfileRest = new UserProfileRest();
        BeanUtils.copyProperties(newUserDTO, userProfileRest);
        
        return userProfileRest;
    }
    
    
}
