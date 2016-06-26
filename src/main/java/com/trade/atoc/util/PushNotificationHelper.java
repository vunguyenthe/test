package com.trade.atoc.util;

import org.jboss.aerogear.unifiedpush.DefaultPushSender;
import org.jboss.aerogear.unifiedpush.PushSender;
import org.jboss.aerogear.unifiedpush.message.MessageResponseCallback;
import org.jboss.aerogear.unifiedpush.message.UnifiedMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trade.atoc.json.JSONParser;
import com.trade.atoc.message.base.BaseMessage;
import com.trade.atoc.system.config.SystemConfiguration;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.security.cert.X509Certificate;
public class PushNotificationHelper {
	private static final Logger logger = LoggerFactory
			.getLogger(PushNotificationHelper.class);
	
	static {
	    disableSslVerification();
	}
	private static final PushSender defaultPushSender = DefaultPushSender
			.withRootServerURL(SystemConfiguration.push_server)
			.pushApplicationId(SystemConfiguration.app_id)
			.masterSecret(SystemConfiguration.master_secret).build();	
	private static void disableSslVerification() {
	    try
	    {
	        // Create a trust manager that does not validate certificate chains
	        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
	            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	                return null;
	            }
	            public void checkClientTrusted(X509Certificate[] certs, String authType) {
	            }
	            public void checkServerTrusted(X509Certificate[] certs, String authType) {
	            }
	        }
	        };

	        // Install the all-trusting trust manager
	        SSLContext sc = SSLContext.getInstance("SSL");
	        sc.init(null, trustAllCerts, new java.security.SecureRandom());
	        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

	        // Create all-trusting host name verifier
	        HostnameVerifier allHostsValid = new HostnameVerifier() {
	            public boolean verify(String hostname, SSLSession session) {
	                return true;
	            }
	        };

	        // Install the all-trusting host verifier
	        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static void requestPushNotificationWithAlias(
			String message) {
		BaseMessage baseMessage = (BaseMessage) JSONParser.fromJsonToObject(message);
		String deviceAlias = baseMessage.getDeviceAlias();
		System.out.println("deviceAlias: " + deviceAlias);
		UnifiedMessage unifiedMessage = UnifiedMessage.withCriteria()
				.aliases(deviceAlias).message().alert(message).build();
		defaultPushSender.send(unifiedMessage, new MessageResponseCallback() {

			@Override
			public void onComplete() {

			}
		});
	}

	public static void requestPushNotification(String message) {
		final UnifiedMessage unifiedMessage = UnifiedMessage.withMessage()
				.alert(message).build();

		defaultPushSender.send(unifiedMessage, new MessageResponseCallback() { // [5]

					@Override
					public void onComplete() {

					}

				});

	}
}
