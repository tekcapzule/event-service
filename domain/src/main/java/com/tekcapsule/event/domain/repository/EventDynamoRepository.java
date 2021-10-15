package com.tekcapsule.event.domain.repository;

import in.devstream.core.domain.CrudRepository;
import com.tekcapsule.event.domain.model.Event;
import com.tekcapsule.event.domain.query.SearchItem;

import java.util.List;

public interface EventDynamoRepository extends CrudRepository<Event, String> {

    void disableById(String tenantId, String id);
    List<SearchItem> search(String tenantId);
}
