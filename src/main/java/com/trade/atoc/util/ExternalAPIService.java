package com.trade.atoc.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.trade.atoc.message.ResponseObj;
import com.trade.atoc.system.config.SystemConfiguration;

/**
 * Created by Vu Nguyen on 6/14/2015.
 */
public class ExternalAPIService {
	// private static final String TAG = "Utilities";
	public static String tenentId;
	public static String basicAuth;
	public static String contentType;
	public static String API_URL;

	public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd",
			new Locale("en"));

	public static ResponseObj getToken(String url_server) {
		ResponseObj resObj = new ResponseObj();
		StringBuilder builder = new StringBuilder();
		HttpClient client = CustomSSLSocketFactory.getNewHttpClient();

		String strAuth = "username=" + SystemConfiguration.keycloak_user + "&password=" + SystemConfiguration.keycloak_pass + "&client_id=security-admin-console&grant_type=password";
		System.out.println(strAuth);
		try {

			HttpPost httpPost = new HttpPost(url_server);
			httpPost.setHeader("Content-Type",
					"application/x-www-form-urlencoded");

			StringEntity se = null;
			se = new StringEntity(strAuth);
			se.setContentType("tex/plain");
			httpPost.setEntity(se);

			HttpResponse response = client.execute(httpPost);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			HttpEntity entity;
			if (statusCode == 200) {
				entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
				resObj.setSuccessResponse(statusCode, builder.toString());
				// Log.d("ApiPostMethod : ", builder.toString());
			} else if (statusCode == 404) {
				resObj.setFailResponse(statusCode, statusCode
						+ " Communication Error.Please try again.");
			} else {
				entity = response.getEntity();
				String content = EntityUtils.toString(entity);
				resObj.setFailResponse(statusCode, content);
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
			resObj.setFailResponse(100, "Unknown Server Address.");
		} catch (ConnectTimeoutException e) {
			e.printStackTrace();
			resObj.setFailResponse(100,
					"Connection timed out.Please try again.");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			resObj.setFailResponse(100, "Please send proper information");
		} catch (Exception e) {
			e.printStackTrace();
			resObj.setFailResponse(100, "Communication Error.Please try again.");
		}
		return resObj;
	}

	public static ResponseObj callExternalApiGetMethod(String server_url,
			String token) {
		StringBuilder builder = new StringBuilder();
		ResponseObj resObj = new ResponseObj();
		HttpClient client = CustomSSLSocketFactory.getNewHttpClient();
		StringBuilder url = new StringBuilder(server_url);

		try {
			HttpGet httpGet = new HttpGet(url.toString());
			httpGet.setHeader("Authorization", "Bearer " + token);
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();

			HttpEntity entity;
			if (statusCode == 200) {
				entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
				resObj.setSuccessResponse(statusCode, builder.toString());
			} else if (statusCode == 404) {
				resObj.setFailResponse(statusCode, statusCode
						+ " Communication Error.Please try again.");
			} else {
				entity = response.getEntity();
				String content = EntityUtils.toString(entity);
				resObj.setFailResponse(statusCode, content);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
			resObj.setFailResponse(100, "Unknown Server Address.");
		} catch (ConnectTimeoutException e) {
			e.printStackTrace();
			resObj.setFailResponse(100,
					"Connection timed out.Please try again.");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			resObj.setFailResponse(100, "Please send proper information");
		} catch (Exception e) {
			e.printStackTrace();
			resObj.setFailResponse(100, "Communication Error.Please try again.");
		}
		return resObj;
	}

	public static ResponseObj callExternalApiPostMethod(String server_url,
			String json, String token) {
		ResponseObj resObj = new ResponseObj();
		StringBuilder builder = new StringBuilder();
		HttpClient client = CustomSSLSocketFactory.getNewHttpClient();
		try {

			HttpPost httpPost = new HttpPost(server_url);
			httpPost.setHeader("Content-Type", "application/json");
			httpPost.setHeader("Authorization", "Bearer " + token);

			StringEntity se = null;
			se = new StringEntity(json);
			se.setContentType("application/json");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
					"application/json"));
			httpPost.setEntity(se);

			HttpResponse response = client.execute(httpPost);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			HttpEntity entity;
			if (statusCode == 200 || statusCode == 201) {
				entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
				resObj.setSuccessResponse(statusCode, builder.toString());
			} else if (statusCode == 404) {
				resObj.setFailResponse(statusCode, statusCode
						+ " Communication Error.Please try again.");
			} else {
				entity = response.getEntity();
				String content = EntityUtils.toString(entity);
				resObj.setFailResponse(statusCode, content);
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
			resObj.setFailResponse(100, "Unknown Server Address.");
		} catch (ConnectTimeoutException e) {
			e.printStackTrace();
			resObj.setFailResponse(100,
					"Connection timed out.Please try again.");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			resObj.setFailResponse(100, "Please send proper information");
		} catch (Exception e) {
			e.printStackTrace();
			resObj.setFailResponse(100, "Communication Error.Please try again.");
		}
		return resObj;
	}

	public static ResponseObj callExternalApiPutMethod(
			String server_url) {
		ResponseObj resObj = new ResponseObj();
		StringBuilder builder = new StringBuilder();
		HttpClient client = CustomSSLSocketFactory.getNewHttpClient();
		JSONObject json = new JSONObject();
		String token = "";
		try {
			HttpPut httpPut = new HttpPut(server_url);
			httpPut.setHeader("X-Obs-Platform-TenantId", "default");
			httpPut.setHeader("Authorization", "Bearer " + token);
			httpPut.setHeader("Content-Type",
					"application/x-www-form-urlencoded");
			StringEntity se = null;
			se = new StringEntity(json.toString());
			se.setContentType("application/json");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
					"application/json"));
			httpPut.setEntity(se);

			HttpResponse response = client.execute(httpPut);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			HttpEntity entity;
			if (statusCode == 200) {
				entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
				resObj.setSuccessResponse(statusCode, builder.toString());
			} else if (statusCode == 404) {
				resObj.setFailResponse(statusCode, statusCode
						+ " Communication Error.Please try again.");
			} else {
				entity = response.getEntity();
				String content = EntityUtils.toString(entity);
				resObj.setFailResponse(statusCode, content);
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
			resObj.setFailResponse(100, "Unknown Server Address.");
		} catch (ConnectTimeoutException e) {
			e.printStackTrace();
			resObj.setFailResponse(100,
					"Connection timed out.Please try again.");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			resObj.setFailResponse(100, "Please send proper information");
		} catch (Exception e) {
			e.printStackTrace();
			resObj.setFailResponse(100, "Communication Error.Please try again.");
		}
		return resObj;
	}

	public static ResponseObj callExternalApiPostMethod(
			String tagURL, JSONObject jsonObj) {
		ResponseObj resObj = new ResponseObj();
		StringBuilder builder = new StringBuilder();
		HttpClient client = CustomSSLSocketFactory.getNewHttpClient();
		String token = "";
		try {

			HttpPost httpPost = new HttpPost(tagURL);
			httpPost.setHeader("Content-Type",
					"application/x-www-form-urlencoded");
			httpPost.setHeader("Authorization", "Bearer " + token);
			StringEntity se = null;
			se = new StringEntity(jsonObj.toString());
			se.setContentType("application/json");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
					"application/json"));
			httpPost.setEntity(se);
			HttpResponse response = client.execute(httpPost);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			HttpEntity entity;
			if (statusCode == 200) {
				entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
				resObj.setSuccessResponse(statusCode, builder.toString());
			} else if (statusCode == 404) {
				resObj.setFailResponse(statusCode, statusCode
						+ " Communication Error.Please try again.");
			} else if (statusCode == 500) {
				resObj.setFailResponse(statusCode, statusCode
						+ " Internal Server Error.");
			} else {
				entity = response.getEntity();
				String content = EntityUtils.toString(entity);
				resObj.setFailResponse(statusCode, content);
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
			resObj.setFailResponse(100, "Unknown Server Address.");
		} catch (ConnectTimeoutException e) {
			e.printStackTrace();
			resObj.setFailResponse(100,
					"Connection timed out.Please try again.");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			resObj.setFailResponse(100, "Please send proper information");
		} catch (Exception e) {
			e.printStackTrace();
			resObj.setFailResponse(100, "Communication Error.Please try again.");
		}
		return resObj;
	}

}
