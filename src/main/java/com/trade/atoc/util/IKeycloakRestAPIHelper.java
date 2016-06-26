package com.trade.atoc.util;

import com.trade.atoc.beans.iToken;

public interface IKeycloakRestAPIHelper {

	public String getServerToken();
	public iToken readJsonUser(String jsonText);
	public String ObjectToJson(Object obj);
	public String getDeviceAlias(String user);

}
