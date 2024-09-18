package com.tekcapzule.event.domain.repository;

import com.tekcapzule.core.domain.CrudRepository;
import com.tekcapzule.event.domain.model.Event;

public interface EventDynamoRepository extends CrudRepository<Event, String> {
}
