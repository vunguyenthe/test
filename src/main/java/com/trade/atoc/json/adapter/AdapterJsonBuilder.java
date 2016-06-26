package com.trade.atoc.json.adapter;

import org.json.JSONObject;

public class AdapterJsonBuilder {
	private static final String CLASS_FIELD = "name";
	private static final String CONTENT_FIELD = "content";
	private static final String TYPE = "type";
	public static String builder(String json) throws ClassNotFoundException {
		JSONObject tmp = new JSONObject(json);
		String className = (String) tmp.get(CLASS_FIELD);
		JSONObject content = tmp.getJSONObject(CONTENT_FIELD);
		JSONObject obj = new JSONObject();
		obj.put(className, content);
		return obj.toString();
	}

	public static String builderATOC(String json) throws ClassNotFoundException {
		JSONObject tmp = new JSONObject(json);
		String className = (String) tmp.get(CLASS_FIELD);
		JSONObject content = tmp.getJSONObject(CONTENT_FIELD);
		JSONObject obj = new JSONObject();
		obj.put(CLASS_FIELD, className);
		obj.put(CONTENT_FIELD, content);
		return obj.toString();
	}
	
}
