package mx.com.basantader.AgenciaViajeTD.handler;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import mx.com.basantader.AgenciaViajeTD.exceptions.BadRequestException;
import mx.com.basantader.AgenciaViajeTD.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTD.exceptions.ResourceNotFoundException;
import mx.com.basantader.AgenciaViajeTD.dto.CustomErrorResponse;
import mx.com.basantader.AgenciaViajeTD.commons.Messages;


import org.slf4j.Logger;

/**
 * Generic error handling mechanism.
 *
 */
@ControllerAdvice
public class ErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseBody
	public CustomErrorResponse handleNotFound(ResourceNotFoundException ex) {
		log.error("Entity was not found", ex);
		return new CustomErrorResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND, ex.getMessage() );
	}
	
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)  // 422
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public CustomErrorResponse handleNotFound(BusinessException ex) {
		 log.error("Generic exception", ex);

		return new CustomErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(),HttpStatus.UNPROCESSABLE_ENTITY, ex.getDescription());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 500
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public CustomErrorResponse handleNotFound(RuntimeException ex) {
		log.error("Generic exception", ex);
		return new CustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value()
				,HttpStatus.INTERNAL_SERVER_ERROR,Messages.getMessage(1));
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)  // 400
	@ExceptionHandler(BadRequestException.class)
	@ResponseBody
	public CustomErrorResponse handleNotFound(BadRequestException ex) {
		log.error("Bad Request", ex);
		return new CustomErrorResponse(HttpStatus.BAD_REQUEST.value()
				,HttpStatus.BAD_REQUEST,ex.getMessage());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)  // 500
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody
	public CustomErrorResponse badRequest(HttpMessageNotReadableException ex) {
		log.error("Bad Request", ex);
		return new CustomErrorResponse(HttpStatus.BAD_REQUEST.value()
				,HttpStatus.BAD_REQUEST,"Bad Requst parametros de entrada no admitidos");
	}


}
