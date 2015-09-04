package com.cherry.application.spring.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cherry.application.spring.service.UserService;
import com.cherry.application.utils.JacksonUtils;
import com.cherry.application.webcommon.ResponseCommon;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/{id}")
	public void findUserInfo(@PathVariable("id") int userId,HttpServletResponse response){
		try {
			ResponseCommon.printJSON(response, userService.findUser(userId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/{id}/find")
	public String findUser(@PathVariable("id") int userId) throws IOException{
		return JacksonUtils.getInstance().writerJavaObject2JSON(userService.findUser(userId));
	}
	
	
	@RequestMapping(value = "/{id}/find2")
	public void findUserResponse(@PathVariable("id") int userId,HttpServletResponse response){
		try {
			ResponseCommon.printJSON(response, userService.findUser(userId));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/{id}/find3")
	@ResponseBody
	public String findUserResponse(@PathVariable("id") int userId) throws IOException{
		return JacksonUtils.getInstance().writerJavaObject2JSON(userService.findUser(userId));
	}
	
}
