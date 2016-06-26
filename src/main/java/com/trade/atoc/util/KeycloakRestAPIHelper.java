package com.trade.atoc.util;

import java.util.List;

import com.google.gson.Gson;
import com.trade.atoc.beans.Client;
import com.trade.atoc.beans.Config;
import com.trade.atoc.beans.Mapper;
import com.trade.atoc.beans.User;
import com.trade.atoc.beans.Users;
import com.trade.atoc.beans.iToken;
import com.trade.atoc.json.JSONParser;
import com.trade.atoc.message.ResponseObj;
import com.trade.atoc.system.config.SystemConfiguration;

public class KeycloakRestAPIHelper implements IKeycloakRestAPIHelper {
	private Gson gson = new Gson();
	public iToken readJsonUser(String jsonText) {
		iToken response = gson.fromJson(jsonText, iToken.class);
		return response;
	}

	public Mapper jsonToMapper(String jsonText) {

		Mapper response = gson.fromJson(jsonText, Mapper.class);
		return response;
	}

	public Client jsonToClient(String jsonText) {
		Gson gson = new Gson();
		Client response = gson.fromJson(jsonText, Client.class);
		return response;
	}
	
	public Users jsonToUsers(String jsonText) {
		Users users = new Users();
		User[] arrUsers = gson.fromJson(jsonText, User[].class);
		for(int i = 0; i < arrUsers.length; i++)
		{
			users.getUsers().add(arrUsers[i]);
		}
		return users;
	}
	
	public User jsonToUser(String jsonText) {
		User user = gson.fromJson(jsonText, User.class);
		return user;
	}
	

	public String ObjectToJson(Object obj) {
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		return json;
	}

	public String getServerToken() {
		String token_url = SystemConfiguration.keycloak_url + "/auth/realms/master/protocol/openid-connect/token";
		System.out.println("tocken url: " + token_url);
		ResponseObj resObj = ExternalAPIService.getToken(token_url);
		
		iToken token = (iToken) readJsonUser(resObj.getsResponse());
		String saccess_token = token.getAccess_token();
		return saccess_token;
	}

	public Users getUsers() {
		System.out.println("->getUsers");
		String token = getServerToken();
		System.out.println("->token: " + token);
		ResponseObj resObj = ExternalAPIService.callExternalApiGetMethod(
				SystemConfiguration.keycloak_url + "/auth/admin/realms/master/users/", token);
		Users users = jsonToUsers(resObj.getsResponse());
		return users;
	}
	public User getUserId(String userId) {
		String token = getServerToken();
		ResponseObj resObj = ExternalAPIService.callExternalApiGetMethod(
				SystemConfiguration.keycloak_url + "/auth/admin/realms/master/users/" + userId, token);
		User user = jsonToUser(resObj.getsResponse());
		return user;
	}
	public String getDeviceAlias(String loginId)
	{
		String deviceAlias = "";
		List<User> listUsers = getUsers().getUsers();
		for(int i = 0; i < listUsers.size(); i ++)
		{
			User user = listUsers.get(i);
			if(user.getUsername().equals(loginId))
			{
				deviceAlias =  user.getEmail();
				break;
			}
		}
		return deviceAlias;
	}
	
}
