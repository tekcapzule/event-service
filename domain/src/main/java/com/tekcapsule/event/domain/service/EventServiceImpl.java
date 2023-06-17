package com.tekcapsule.event.domain.service;

import com.tekcapsule.event.domain.command.CreateCommand;
import com.tekcapsule.event.domain.command.DisableCommand;
import com.tekcapsule.event.domain.command.UpdateCommand;
import com.tekcapsule.event.domain.model.Event;
import com.tekcapsule.event.domain.model.Status;
import com.tekcapsule.event.domain.repository.EventDynamoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Slf4j
@Service
public class EventServiceImpl implements EventService {

    private EventDynamoRepository eventDynamoRepository;

    @Autowired
    public EventServiceImpl(EventDynamoRepository eventDynamoRepository) {
        this.eventDynamoRepository = eventDynamoRepository;
    }

    @Override
    public void create(CreateCommand createCommand) {

        log.info(String.format("Entering create event service - Event Name:%s", createCommand.getName()));

        String code= UUID.randomUUID().toString();
        Event event = Event.builder()
                .code(code)
                .name(createCommand.getName())
                .summary(createCommand.getSummary())
                .description(createCommand.getDescription())
                .eventDate(createCommand.getEventDate())
                .imageUrl(createCommand.getImageUrl())
                .registrationUrl(createCommand.getRegistrationUrl())
                .eventRecordingUrl(createCommand.getEventRecordingUrl())
                .pastPopularEvent(createCommand.getPastPopularEvent())
                .schedule(createCommand.getSchedule())
                .venue(createCommand.getVenue())
                .region(createCommand.getRegion())
                .promotion(createCommand.getPromotion())
                .status(Status.ACTIVE)
                .build();
        event.setAddedOn(createCommand.getExecOn());
        event.setUpdatedOn(createCommand.getExecOn());
        event.setAddedBy(createCommand.getExecBy().getUserId());

        eventDynamoRepository.save(event);
    }

    @Override
    public void update(UpdateCommand updateCommand) {

        log.info(String.format("Entering update event service - Event Code:%s", updateCommand.getCode()));

        Event event = eventDynamoRepository.findBy(updateCommand.getCode());
        if (event != null) {

            event.setName(updateCommand.getName());
            event.setSummary(updateCommand.getSummary());
            event.setDescription(updateCommand.getDescription());
            event.setEventDate(updateCommand.getEventDate());
            event.setImageUrl(updateCommand.getImageUrl());
            event.setRegistrationUrl(updateCommand.getRegistrationUrl());
            event.setSchedule(updateCommand.getSchedule());
            event.setVenue(updateCommand.getVenue());
            event.setRegion(updateCommand.getRegion());
            event.setEventRecordingUrl(updateCommand.getEventRecordingUrl());
            event.setPastPopularEvent(updateCommand.getPastPopularEvent());
            event.setPromotion(updateCommand.getPromotion());
            event.setUpdatedOn(updateCommand.getExecOn());
            event.setUpdatedBy(updateCommand.getExecBy().getUserId());

            eventDynamoRepository.save(event);
        }
    }

    @Override
    public void disable(DisableCommand disableCommand) {

        log.info(String.format("Entering disable event service - Event Code:%s", disableCommand.getCode()));

        Event event = eventDynamoRepository.findBy(disableCommand.getCode());
        if (event != null) {
            event.setStatus(Status.INACTIVE);
            event.setUpdatedOn(disableCommand.getExecOn());
            event.setUpdatedBy(disableCommand.getExecBy().getUserId());
            eventDynamoRepository.save(event);
        }
    }

    @Override
    public Event findBy(String code) {
        log.info(String.format("Entering find by event service - Event Code:%s", code));

        return eventDynamoRepository.findBy(code);
    }

    @Override
    public List<Event> findAll() {
        log.info("Entering find all events service");

        return eventDynamoRepository.findAll();        }

}
