package com.trade.atoc.stories;

import com.trade.atoc.common.BaseStep;
import org.jbehave.core.annotations.*;

import java.util.ArrayList;
import java.util.List;

public class CommonSteps extends BaseStep {

    protected String mDeviceId;
    private List<String> mappingLocation = new ArrayList<String>();

    @BeforeScenario
    public void init() {

        // initializeStationWorker();
    }

    @Given("the DeviceId is <device_id>")
    public void createAccount(@Named("device_id") String deviceId) {
        mDeviceId = deviceId;
        System.out.println("mDeviceId -> " + mDeviceId);

    }

    @Then("TAS gets deviceId is <device_id>")
    public void validateDeviceId(@Named("device_id") String deviceId) {
        mDeviceId = deviceId;
        System.out.println("deviceId -> " + deviceId);

    }

    @When("$sourceSystem sends to $destinationSystem a message $message with $content")
    public void sendMessage(@Named("sourceSystem") String sourceSystem,
                            @Named("destinationSystem") String destinationSystem,
                            @Named("message") String message, @Named("content") String content) {
        System.out.println("sourceSystem -> " + sourceSystem);
        System.out.println("destinationSystem -> " + destinationSystem);
        System.out.println("message -> " + message);
        System.out.println("content -> " + content);
        // BaseMessage parsedMessage = parseMessageFromJson(mDeviceId,
        // sourceSystem, message, content);

        // String json = JSONParser.toJson(parsedMessage);
        sendMessageToTAS(sourceSystem, content);
    }

    @Then("$sourceSystem sends to $destinationSystem a message $message with $content")
    public void validateMessage(@Named("sourceSystem") String sourceSystem,
                                @Named("destinationSystem") String destinationSystem,
                                @Named("message") String message, @Named("content") String content) {

        // BaseMessage expectedMessage = parseMessageFromJson(stationId,
        // destinationSystem, message, content);

        // String jsonEvent = receiveMessageFromSc(destinationSystem);
        // BaseMessage actualMessage = JSONParser.fromJsonToMessage(jsonEvent);

        // Assert.assertEquals(stationId, actualMessage.getStationId());

        // Assert.assertEquals(true, MessageMatcherFactory.create(actualMessage)
        // .match(expectedMessage));
    }
}