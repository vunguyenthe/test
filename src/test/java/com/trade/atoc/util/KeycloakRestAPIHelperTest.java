package com.trade.atoc.util;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.trade.atoc.beans.Users;
import com.trade.atoc.json.JSONParser;
import com.trade.atoc.system.config.SystemConfiguration;

public class KeycloakRestAPIHelperTest {
	private KeycloakRestAPIHelper keycloakRestAPIHelper = new KeycloakRestAPIHelper();
	private static String token = "";

	@Before
	public void init() {
		token = keycloakRestAPIHelper.getServerToken();
		System.out.println("token: " + token);
		assertNotNull(token);

	}
	@Test
	public void testGetUsers()
	{
		Users users = keycloakRestAPIHelper.getUsers();
		JSONParser.registerType(Users.class);
		String json = JSONParser.toJson(users);
		System.out.println(json);
	}
	//@Test
	public void testLoadPropertieseConfiguration(){
		SystemConfiguration.loadPropertieseConfiguration();
		boolean b1 = false;
		if(SystemConfiguration.keycloak_user.equals("admind9uaded"))
		{
			b1 = true;
		}
		equals(b1);
	}
}
