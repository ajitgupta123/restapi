/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdeveloperblog.app.ws.exception;

import com.appdeveloperblog.app.ws.ui.model.response.ErrorMessage;
import com.appdeveloperblog.app.ws.ui.model.response.ErrorMessages;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author sergeykargopolov
 */
@Provider
public class CouldNotCreateRecordExceptionMapper implements ExceptionMapper<CouldNotCreateRecordException>{

    public Response toResponse(CouldNotCreateRecordException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(),
                ErrorMessages.RECORD_ALREADY_EXISTS.name(), "http://appsdeveloperblog.com");
        
      return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
    }
}
