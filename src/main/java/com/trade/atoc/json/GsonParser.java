package com.trade.atoc.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.manualoverride.adapter.OcadoGsonBuilder;
import com.manualoverride.argonaut.GsonOperations;
import com.manualoverride.argonaut.JsonOperationsSerialiser;
import com.manualoverride.argonaut.JsonSerialiser;

/**
 * Used to serialise/deserialise message received from wcs conveyor from Object
 * to String and opposition
 * 
 */
public class GsonParser {

	private static final Logger logger = LoggerFactory
			.getLogger(GsonParser.class);

	private static final Gson gson = OcadoGsonBuilder.getGsonBuilder().create();


	/**
	 * Build object to json
	 * 
	 * @param request
	 *            {@link Object}
	 */
	public static String toJson(Object object) {

		if (object == null) {
			return null;
		}
		return gson.toJson(object);
	}

	/**
	 * Build json to object
	 * 
	 * @param request
	 *            {@link String}
	 */
	public static Object fromJsonToObject(String json, Class<?> clazz) {

		try {
			return (Object) gson.fromJson(json, clazz);
		} catch (RuntimeException e) {
			logger.error("JSONParser.fromJson() -> Error [" + e.getMessage()
					+ "] when trying to convert json (" + json
					+ ") to BaseMessage");
			return null;
		}
	}

}