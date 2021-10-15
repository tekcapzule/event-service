package com.tekcapsule.event.domain.service;

import com.tekcapsule.event.domain.command.CreateCommand;
import com.tekcapsule.event.domain.command.DisableCommand;
import com.tekcapsule.event.domain.command.UpdateCommand;
import com.tekcapsule.event.domain.model.Event;

public interface EventService {

    Event create(CreateCommand createCommand);

    Event update(UpdateCommand updateCommand);

    void disable(DisableCommand disableCommand);

    Event get(String eventId);
}
