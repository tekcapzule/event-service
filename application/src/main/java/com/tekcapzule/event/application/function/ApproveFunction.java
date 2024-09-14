package com.tekcapzule.event.application.function;

import com.tekcapzule.core.domain.Origin;
import com.tekcapzule.core.utils.HeaderUtil;
import com.tekcapzule.core.utils.Outcome;
import com.tekcapzule.core.utils.PayloadUtil;
import com.tekcapzule.core.utils.Stage;
import com.tekcapzule.event.application.config.AppConfig;
import com.tekcapzule.event.application.function.input.ApproveEventInput;
import com.tekcapzule.event.application.mapper.InputOutputMapper;
import com.tekcapzule.event.domain.command.ApproveCommand;
import com.tekcapzule.event.domain.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
@Slf4j
public class ApproveFunction implements Function<Message<ApproveEventInput>, Message<Void>> {

    private final EventService eventService;

    private final AppConfig appConfig;

    public ApproveFunction(final EventService eventService, final AppConfig appConfig) {
        this.eventService = eventService;
        this.appConfig = appConfig;
    }

    @Override
    public Message<Void> apply(Message<ApproveEventInput> approveEventInputMessage) {
        Map<String, Object> responseHeaders = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();
        String stage = appConfig.getStage().toUpperCase();
        try {
            ApproveEventInput approveEventInput = approveEventInputMessage.getPayload();
            log.info(String.format("Entering approve event Function -  Event Code:%s", approveEventInput.getCode()));
            Origin origin = HeaderUtil.buildOriginFromHeaders(approveEventInputMessage.getHeaders());
            ApproveCommand approveCommand = InputOutputMapper.buildApproveCommandFromApproveEventInput.apply(approveEventInput, origin);
            eventService.approve(approveCommand);
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