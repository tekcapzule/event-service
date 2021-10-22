package com.tekcapsule.event.domain.service;

import com.tekcapsule.event.domain.command.CreateCommand;
import com.tekcapsule.event.domain.command.DisableCommand;
import com.tekcapsule.event.domain.command.UpdateCommand;
import com.tekcapsule.event.domain.model.Event;
import com.tekcapsule.event.domain.repository.EventDynamoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

        log.info(String.format("Entering create event service - Event Code:%S", createCommand.getName()));

        Event event = Event.builder()
                .code(createCommand.getCode())
                .name(createCommand.getName())
                .description(createCommand.getDescription())
                .eventDate(createCommand.getEventDate())
                .imageUrl(createCommand.getImageUrl())
                .registrationUrl(createCommand.getRegistrationUrl())
                .active(true)
                .build();
        event.setAddedOn(createCommand.getExecOn());
        event.setUpdatedOn(createCommand.getExecOn());
        event.setAddedBy(createCommand.getExecBy().getUserId());

        return eventDynamoRepository.save(event);
    }

    @Override
    public Event update(UpdateCommand updateCommand) {

        log.info(String.format("Entering update event service - Event Code:%S", updateCommand.getCode()));

        Event event = eventDynamoRepository.findBy(updateCommand.getCode());
        if (event != null) {

            event.setName(updateCommand.getName());
            event.setDescription(updateCommand.getDescription());
            event.setEventDate(updateCommand.getEventDate());
            event.setImageUrl(updateCommand.getImageUrl());
            event.setRegistrationUrl(updateCommand.getRegistrationUrl());
            event.setUpdatedOn(updateCommand.getExecOn());
            event.setUpdatedBy(updateCommand.getExecBy().getUserId());

            eventDynamoRepository.save(event);
        }
        return event;
    }

    @Override
    public void disable(DisableCommand disableCommand) {

        log.info(String.format("Entering disable event service - Event Code:%S", disableCommand.getCode()));

        Event event = eventDynamoRepository.findBy(disableCommand.getCode());
        if (event != null) {
            event.setActive(false);
            event.setUpdatedOn(disableCommand.getExecOn());
            event.setUpdatedBy(disableCommand.getExecBy().getUserId());
            eventDynamoRepository.save(event);
        }
    }

    @Override
    public Event findBy(String code) {
        log.info(String.format("Entering find by event service - Event Code:%S", code));

        return eventDynamoRepository.findBy(code);
    }

    @Override
    public List<Event> findAll() {
        log.info("Entering find all events service");

        return eventDynamoRepository.findAll();        }

}
