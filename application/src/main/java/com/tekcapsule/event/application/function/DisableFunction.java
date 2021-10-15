package com.tekcapsule.event.application.function;

import com.tekcapsule.core.domain.Origin;
import com.tekcapsule.core.utils.HeaderUtil;
import com.tekcapsule.event.application.mapper.InputOutputMapper;
import com.tekcapsule.event.domain.command.DisableCommand;
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
public class DisableFunction implements Function<Message<DisableInput>, Message<Void>> {

    private final EventService eventService;

    public DisableFunction(final EventService eventService) {
        this.eventService = eventService;
    }


    @Override
    public Message<Void> apply(Message<DisableInput> disableInputMessage) {

        DisableInput disableInput = disableInputMessage.getPayload();

        log.info(String.format("Entering disable mentor Function - User Id:{1}", disableInput.getUserId()));

        Origin origin = HeaderUtil.buildOriginFromHeaders(disableInputMessage.getHeaders());

        DisableCommand disableCommand = InputOutputMapper.buildDisableCommandFromDisableInput.apply(disableInput, origin);
        eventService.disable(disableCommand);
        Map<String, Object> responseHeader = new HashMap();
        responseHeader.put(AppConstants.HTTP_STATUS_CODE_HEADER, HttpStatus.OK.value());

        return new GenericMessage( responseHeader);
    }
}
