package com.trade.atoc.common;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.trade.atoc.json.JSONParser;
import com.trade.atoc.message.base.BaseMessage;
import junit.framework.Assert;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseStep {

    private static final Logger logger = LoggerFactory
            .getLogger(BaseStep.class);

    private Map<String, String> eventNameToClassMap;
    private static final String MESSAGE_TYPE = "type";
    private static final String STATION_ID_KEY = "deviceId";
    private static final String MESSAGE_CONTENT = "content";

    private Map<MOSystem, SimpleCommunicationSystem> mockingServices;

    /**
     * @param tradeAuthorizationUrl the stationControllerUrl to set
     */
    public void setTradeAuthorizationUrl(String tradeAuthorizationUrl) {

        this.tradeAuthorizationUrl = tradeAuthorizationUrl;
    }

    private String tradeAuthorizationUrl;

    public BaseStep() {

        mockingServices = new HashMap<MOSystem, SimpleCommunicationSystem>();
        mockingServices.put(MOSystem.ATOC, new SimpleCommunicationSystem());
        mockingServices.put(MOSystem.Mobile, new SimpleCommunicationSystem());
    }

    public void initializeATOCSystem() {

        ClientResponse response = Client.create()
                .resource(tradeAuthorizationUrl).get(ClientResponse.class);
        Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
        Assert.assertNotNull(response.getEntity(String.class));
    }

    public BaseMessage parseMessageFromJson(String deviceId,
                                            String messageSource, String messageType, String content) {

        StringBuilder eventName = new StringBuilder();
        eventName.append(messageSource);
        eventName.append(messageType);
        String className = eventNameToClassMap.get(eventName.toString());
        JSONObject messageContent = new JSONObject(content);

        messageContent.put(STATION_ID_KEY,
                new JSONObject().put("value", deviceId));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(MESSAGE_TYPE, className);
        jsonObject.put(MESSAGE_CONTENT, messageContent);

        return (BaseMessage) JSONParser.fromJsonToMessage(jsonObject.toString());
    }

    public String receiveMessageFrom(String destination) {

        SimpleCommunicationSystem mockService = mockingServices.get(MOSystem
                .valueOf(destination));
        if (mockService != null) {
            return mockService.getReceivedMessage();
        }
        return null;
    }

    public void sendMessageToTAS(String source, String jsonMessage) {

        SimpleCommunicationSystem mockService = mockingServices.get(MOSystem
                .valueOf(source));

        try {
            if (mockService != null) {
                System.out.println("sendMessageToTAS " + jsonMessage);
                mockService.getPublisher().sendMessages(jsonMessage);
            }
        } catch (JMSException e) {
            logger.debug("Send message failed : " + e.toString(), e);
        }
    }

    public void setEventNameToClassMap(Map<String, String> eventNameToClassMap) {

        this.eventNameToClassMap = eventNameToClassMap;
    }

}
