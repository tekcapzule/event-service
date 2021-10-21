package com.tekcapsule.event.application.function;

import com.tekcapsule.event.application.config.AppConstants;
import com.tekcapsule.event.domain.model.Event;
import com.tekcapsule.event.domain.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class GetAllFunction implements Function<Message<Void>, Message<List<Event>>> {

    private final EventService eventService;

    public GetAllFunction(final EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public Message<List<Event>> apply(Message<Void> findAllMessage) {

        log.info(String.format("Entering get all events Function"));

        List<Event> events = eventService.findAll();
        Map<String, Object> responseHeader = new HashMap();
        responseHeader.put(AppConstants.HTTP_STATUS_CODE_HEADER, HttpStatus.NOT_FOUND.value());

        return new GenericMessage(events, responseHeader);
    }
}