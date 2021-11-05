package com.tekcapsule.event.application.function;

import com.tekcapsule.core.domain.Origin;
import com.tekcapsule.core.utils.HeaderUtil;
import com.tekcapsule.core.utils.Outcome;
import com.tekcapsule.core.utils.PayloadUtil;
import com.tekcapsule.core.utils.Stage;
import com.tekcapsule.event.application.config.AppConfig;
import com.tekcapsule.event.application.function.input.DisableInput;
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

    private final AppConfig appConfig;

    public DisableFunction(final EventService eventService, final AppConfig appConfig) {
        this.eventService = eventService;
        this.appConfig = appConfig;
    }


    @Override
    public Message<Void> apply(Message<DisableInput> disableInputMessage) {
        Map<String, Object> responseHeaders = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();
        String stage = appConfig.getStage().toUpperCase();
        try {
            DisableInput disableInput = disableInputMessage.getPayload();
            log.info(String.format("Entering disable event Function - Event Code:%s", disableInput.getCode()));
            Origin origin = HeaderUtil.buildOriginFromHeaders(disableInputMessage.getHeaders());
            DisableCommand disableCommand = InputOutputMapper.buildDisableCommandFromDisableInput.apply(disableInput, origin);
            eventService.disable(disableCommand);
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
            payload = PayloadUtil.composePayload(Outcome.SUCCESS);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
            payload = PayloadUtil.composePayload(Outcome.ERROR);
        }
        return new GenericMessage(payload, responseHeaders);
    }
}
