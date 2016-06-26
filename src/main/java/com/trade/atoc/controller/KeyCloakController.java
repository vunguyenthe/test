package com.trade.atoc.controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletConfigAware;

import com.trade.atoc.beans.User;
import com.trade.atoc.beans.Users;
import com.trade.atoc.service.UserService;
import com.trade.atoc.system.config.SystemConfiguration;
import com.trade.atoc.util.KeycloakRestAPIHelper;
@RestController
public class KeyCloakController implements ServletConfigAware {

	@Autowired
	private ServletContext mServletContext;
	@Autowired
	UserService userService; // Service which will do all data
	private KeycloakRestAPIHelper keycloakRestAPIHelper = new KeycloakRestAPIHelper();
	public void setServletConfig(ServletConfig servletConfig) {
		mServletContext = servletConfig.getServletContext();
	}

	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	@ResponseBody
	public Users getUsers() {
		Users users = keycloakRestAPIHelper.getUsers();
		return users;

	}
	@RequestMapping(value = "/getUserId/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public User getUserId(@PathVariable String userId) {
		System.out.println("userId:" + userId);
		User user = keycloakRestAPIHelper.getUserId(userId);
		return user;

	}
}
