package com.tekcapsule.event.domain.service;

import com.tekcapsule.event.domain.command.CreateCommand;
import com.tekcapsule.event.domain.command.DisableCommand;
import com.tekcapsule.event.domain.command.UpdateCommand;
import com.tekcapsule.event.domain.model.Event;
import com.tekcapsule.event.domain.repository.EventDynamoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class EventServiceImpl implements EventService {

    private EventDynamoRepository eventDynamoRepository;

    @Autowired
    public EventServiceImpl(EventDynamoRepository eventDynamoRepository) {
        this.eventDynamoRepository = eventDynamoRepository;
    }

    @Override
    public Event create(CreateCommand createCommand) {

        log.info(String.format("Entering create event service - Event Name:{0}", createCommand.getName()));

        Event event = Event.builder()
                .active(true)
                .build();
        event.setAddedOn(createCommand.getExecOn());
        event.setUpdatedOn(createCommand.getExecOn());
        event.setAddedBy(createCommand.getExecBy().getUserId());

        return eventDynamoRepository.save(event);
    }

    @Override
    public Event update(UpdateCommand updateCommand) {

        log.info(String.format("Entering update event service - Event Id:{0}", updateCommand.getCode()));

        Event event = eventDynamoRepository.findBy(updateCommand.getCode());
        if (event != null) {

            event.setUpdatedOn(updateCommand.getExecOn());
            event.setUpdatedBy(updateCommand.getExecBy().getUserId());

            eventDynamoRepository.save(event);
        }
        return event;
    }

    @Override
    public void disable(DisableCommand disableCommand) {

        log.info(String.format("Entering disable event service - Event Id:{0}", disableCommand.getCode()));

        eventDynamoRepository.disable(disableCommand.getCode());
    }

    @Override
    public Event get(String eventId) {

        log.info(String.format("Entering get event service - Event Id:{0}", eventId));

        return eventDynamoRepository.findBy(eventId);
    }
}
