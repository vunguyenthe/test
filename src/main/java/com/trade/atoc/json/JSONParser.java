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
public class JSONParser {

	private static final Logger logger = LoggerFactory
			.getLogger(JSONParser.class);

	private static final Gson gson = OcadoGsonBuilder.getGsonBuilder().create();
	private static final JsonSerialiser jsonSerialiser = new JsonOperationsSerialiser<>(
			new GsonOperations(gson));

	/**
	 * Register the type with inputed class for jsonSerialiser
	 * {@link JsonSerialiser}
	 * 
	 * @param clazz
	 *            {@link Class}
	 */
	public static void registerType(Class<?> clazz) {

		jsonSerialiser.registerType(clazz);
	}

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
		return jsonSerialiser.serialise(object);
	}

	/**
	 * Build json to object
	 * 
	 * @param request
	 *            {@link String}
	 */
	public static Object fromJsonToMessage(String json) {

		try {
			return (Object) jsonSerialiser.deserialise(json);
		} catch (RuntimeException e) {
			logger.error("JSONParser.fromJson() -> Error [" + e.getMessage()
					+ "] when trying to convert json (" + json
					+ ") to BaseMessage");
			return null;
		}
	}

	/**
	 * Build json to object
	 * 
	 * @param request
	 *            {@link String}
	 */
	public static Object fromJsonToObject(String json) {
		try {
			return jsonSerialiser.deserialise(json);
		} catch (RuntimeException e) {
			logger.error("JSONParser.fromJson() -> Error [" + e.getMessage()
					+ "] when trying to convert json (" + json
					+ ") to BaseMessage");
			return null;
		}
	}
}