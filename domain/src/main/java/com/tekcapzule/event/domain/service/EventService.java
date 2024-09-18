package com.tekcapzule.event.domain.service;

import com.tekcapzule.event.domain.command.ApproveCommand;
import com.tekcapzule.event.domain.command.CreateCommand;
import com.tekcapzule.event.domain.command.DisableCommand;
import com.tekcapzule.event.domain.command.UpdateCommand;
import com.tekcapzule.event.domain.model.Event;

import java.util.List;

public interface EventService {

    void create(CreateCommand createCommand);

    void update(UpdateCommand updateCommand);

    void disable(DisableCommand disableCommand);

    Event findBy(String code);

    List<Event> findAll();
    void approve(ApproveCommand approveCommand);

}
