package com.tekcapsule.event.domain.repository;

import com.tekcapsule.core.domain.CrudRepository;
import com.tekcapsule.event.domain.model.Event;

public interface EventDynamoRepository extends CrudRepository<Event, String> {

    void disable(String code);
}
