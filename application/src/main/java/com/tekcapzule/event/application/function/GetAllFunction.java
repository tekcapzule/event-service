package com.tekcapzule.event.application.function;

import com.tekcapzule.core.domain.EmptyFunctionInput;
import com.tekcapzule.core.utils.HeaderUtil;
import com.tekcapzule.core.utils.Outcome;
import com.tekcapzule.core.utils.Stage;
import com.tekcapzule.event.application.config.AppConfig;
import com.tekcapzule.event.domain.model.Event;
import com.tekcapzule.event.domain.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class GetAllFunction implements Function<Message<EmptyFunctionInput>, Message<List<Event>>> {

    private final EventService eventService;

    private final AppConfig appConfig;

    public GetAllFunction(final EventService eventService, final AppConfig appConfig) {
        this.eventService = eventService;
        this.appConfig = appConfig;
    }

    @Override
    public Message<List<Event>> apply(Message<EmptyFunctionInput> findAllMessage) {
        Map<String, Object> responseHeaders = new HashMap<>();
        List<Event> events = new ArrayList<>();
        String stage = appConfig.getStage().toUpperCase();
        try {
            log.info("Entering get all events Function");
            events = eventService.findAll();
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
        }
        return new GenericMessage(events, responseHeaders);
    }
}