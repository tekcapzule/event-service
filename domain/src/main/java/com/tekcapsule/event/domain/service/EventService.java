package com.tekcapsule.event.domain.service;

import com.tekcapsule.event.domain.command.CreateCommand;
import com.tekcapsule.event.domain.command.DisableCommand;
import com.tekcapsule.event.domain.command.UpdateCommand;
import com.tekcapsule.event.domain.model.Event;

import java.util.List;

public interface EventService {

    Event create(CreateCommand createCommand);

    Event update(UpdateCommand updateCommand);

    void disable(DisableCommand disableCommand);

    Event findBy(String code);

    List<Event> findAll();

}
