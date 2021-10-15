package com.tekcapsule.event.application.function;

import com.tekcapsule.event.application.function.input.UpdateInput;
import com.tekcapsule.event.domain.command.UpdateCommand;
import com.tekcapsule.event.domain.model.Event;
import com.tekcapsule.event.domain.service.EventService;
import in.devstream.core.domain.Origin;
import in.devstream.core.utils.HeaderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.tekcapsule.event.application.config.AppConstants.HTTP_STATUS_CODE_HEADER;

@Component
@Slf4j
public class UpdateFunction implements Function<Message<UpdateInput>, Message<Event>> {

    private final EventService eventService;

    public UpdateFunction(final EventService eventService) {
        this.eventService = eventService;
    }


    @Override
    public Message<Event> apply(Message<UpdateInput> updateInputMessage) {
        UpdateInput updateInput = updateInputMessage.getPayload();

        log.info(String.format("Entering update mentor Function - User Id:{1}",  updateInput.getUserId()));

        Origin origin = HeaderUtil.buildOriginFromHeaders(updateInputMessage.getHeaders());

        UpdateCommand updateCommand = InputOutputMapper.buildUpdateCommandFromUpdateInput.apply(updateInput, origin);
        Event event = eventService.update(updateCommand);
        Map<String, Object> responseHeader = new HashMap();
        responseHeader.put(HTTP_STATUS_CODE_HEADER, HttpStatus.OK.value());

        return new GenericMessage(event, responseHeader);

    }
}