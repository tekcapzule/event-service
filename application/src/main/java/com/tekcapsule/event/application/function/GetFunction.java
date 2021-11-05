package com.tekcapsule.event.application.function;

import com.tekcapsule.core.utils.HeaderUtil;
import com.tekcapsule.core.utils.Outcome;
import com.tekcapsule.core.utils.Stage;
import com.tekcapsule.event.application.config.AppConfig;
import com.tekcapsule.event.application.function.input.GetInput;
import com.tekcapsule.event.domain.model.Event;
import com.tekcapsule.event.domain.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class GetFunction implements Function<Message<GetInput>, Message<Event>> {

    private final EventService eventService;

    private final AppConfig appConfig;

    public GetFunction(final EventService eventService, final AppConfig appConfig) {
        this.eventService = eventService;
        this.appConfig = appConfig;
    }


    @Override
    public Message<Event> apply(Message<GetInput> getInputMessage) {
        Map<String, Object> responseHeaders = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();
        Event event = new Event();
        String stage = appConfig.getStage().toUpperCase();
        try {
            GetInput getInput = getInputMessage.getPayload();
            log.info(String.format("Entering find by event Function - Event Code:%s}", getInput.getCode()));
            event = eventService.findBy(getInput.getCode(), getInput.getEventDate());
            Map<String, Object> responseHeader = new HashMap<>();
            if (event == null) {
                responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.NOT_FOUND);
                event = Event.builder().build();
            } else {
                responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
        }
        return new GenericMessage(event, responseHeaders);
    }
}