/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdeveloperblog.app.ws.ui.entrypoints;

import com.appdeveloperblog.app.ws.service.AuthenticationService;
import com.appdeveloperblog.app.ws.service.AuthenticationServiceImpl;
import com.appdeveloperblog.app.ws.shared.dto.UserDTO;
import com.appdeveloperblog.app.ws.ui.model.request.LoginCredentials;
import com.appdeveloperblog.app.ws.ui.model.response.AuthenticationDetails;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author gts
 */
@Path("/authentication")
public class AuthenticationEntryPoint {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public AuthenticationDetails userLogin(LoginCredentials loginCredentials)
    {
        AuthenticationDetails returnValue = new AuthenticationDetails();
        
        AuthenticationService authenticationService = new AuthenticationServiceImpl();
        UserDTO authenticatedUser = authenticationService.authenticate(loginCredentials.getUserName(), loginCredentials.getUserPassword());

        
        String accessToken = authenticationService.issueAccessToken(authenticatedUser);
        
        returnValue.setId(authenticatedUser.getUserId());
        returnValue.setToken(accessToken);
        
//        returnValue.setId("12345dfsv");
//        returnValue.setToken("hffhlk90348539kldj");

        return returnValue;
        
    } 
    
}
