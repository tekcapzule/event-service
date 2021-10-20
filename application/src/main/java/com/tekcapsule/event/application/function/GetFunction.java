package com.tekcapsule.event.application.function;

import com.tekcapsule.event.application.config.AppConstants;
import com.tekcapsule.event.application.function.input.GetInput;
import com.tekcapsule.event.domain.model.Event;
import com.tekcapsule.event.domain.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    public GetFunction(final EventService eventService) {
        this.eventService = eventService;
    }


    @Override
    public Message<Event> apply(Message<GetInput> getInputMessage) {
        GetInput getInput = getInputMessage.getPayload();

        log.info(String.format("Entering find by event Function - Event Code:{0}",  getInput.getCode()));

        Event event = eventService.findBy(getInput.getCode());
        Map<String, Object> responseHeader = new HashMap();
        if (event == null) {
            responseHeader.put(AppConstants.HTTP_STATUS_CODE_HEADER, HttpStatus.NOT_FOUND.value());
            event = Event.builder().build();
        } else {
            responseHeader.put(AppConstants.HTTP_STATUS_CODE_HEADER, HttpStatus.OK.value());
        }
        return new GenericMessage(event, responseHeader);
    }
}