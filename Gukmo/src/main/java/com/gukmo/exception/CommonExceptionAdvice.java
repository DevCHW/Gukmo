package com.gukmo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class CommonExceptionAdvice {
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex) {
		return "error/404";
	}
	
	
	@ExceptionHandler(Exception.class)
	public String except(Exception e, Model model) {
		// 예외가 발생하게 되면 해당 예외 필드가 메모리에 할당된다.
		// 할당된 예외 필드의 주소 값을 받을 객체가 필요하므로 매개변수에 Exception타입의
		// e 객체를 선언해놓는다.		
		model.addAttribute("exception", e);
		return "error/500";
	}

}
