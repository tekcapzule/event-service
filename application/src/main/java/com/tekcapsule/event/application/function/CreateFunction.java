package com.tekcapsule.event.application.function;

import com.tekcapsule.core.domain.Origin;
import com.tekcapsule.core.utils.HeaderUtil;
import com.tekcapsule.event.application.config.AppConstants;
import com.tekcapsule.event.application.function.input.CreateInput;
import com.tekcapsule.event.application.mapper.InputOutputMapper;
import com.tekcapsule.event.domain.command.CreateCommand;
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
public class CreateFunction implements Function<Message<CreateInput>, Message<Event>> {

    private final EventService eventService;

    public CreateFunction(final EventService eventService) {
        this.eventService = eventService;
    }


    @Override
    public Message<Event> apply(Message<CreateInput> createInputMessage) {

        CreateInput createInput = createInputMessage.getPayload();

        log.info(String.format("Entering create event Function - Event Name:{0}",createInput.getName().toString()));

        Origin origin = HeaderUtil.buildOriginFromHeaders(createInputMessage.getHeaders());

        CreateCommand createCommand = InputOutputMapper.buildCreateCommandFromCreateInput.apply(createInput, origin);
        Event event = eventService.create(createCommand);
        Map<String, Object> responseHeader = new HashMap();
        responseHeader.put(AppConstants.HTTP_STATUS_CODE_HEADER, HttpStatus.OK.value());

        return new GenericMessage(event, responseHeader);
    }
}