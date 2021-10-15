package com.tekcapsule.event.application.function;

import com.tekcapsule.event.domain.query.SearchItem;
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
public class SearchFunction implements Function<Message<SearchInput>, Message<List<SearchItem>>> {

    private final EventService eventService;

    public SearchFunction(final EventService eventService) {
        this.eventService = eventService;
    }


    @Override
    public Message<List<SearchItem>> apply(Message<SearchInput> searchInputMessage) {
        SearchInput searchInput = searchInputMessage.getPayload();

        log.info(String.format("Entering search mentor Function - Tenant Id:{0}", searchInput.getTenantId()));

        List<SearchItem> searchItems = mentorService.search(SearchQuery.builder().tenantId(searchInput.getTenantId()).build());
        Map<String, Object> responseHeader = new HashMap();
        responseHeader.put(AppConstants.HTTP_STATUS_CODE_HEADER, HttpStatus.OK.value());

        return new GenericMessage(searchItems, responseHeader);
    }
}