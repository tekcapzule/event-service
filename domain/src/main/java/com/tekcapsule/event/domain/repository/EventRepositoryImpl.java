package com.tekcapsule.event.domain.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.tekcapsule.event.domain.model.Event;
import com.tekcapsule.event.domain.query.SearchItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class EventRepositoryImpl implements EventDynamoRepository {

    private DynamoDBMapper dynamo;

    @Autowired
    public CapsuleRepositoryImpl(DynamoDBMapper dynamo) {
        this.dynamo = dynamo;
    }

    @Override
    public List<Event> findAll(String tenantId) {

        Event hashKey = Event.builder().tenantId(tenantId).build();
        DynamoDBQueryExpression<Event> queryExpression = new DynamoDBQueryExpression<Event>()
                .withHashKeyValues(hashKey);

        return dynamo.query(Event.class, queryExpression);
    }

    @Override
    public Event findBy(String tenantId, String userId) {
        return dynamo.load(Event.class, tenantId, userId);
    }

    @Override
    public Event save(Event event) {
        dynamo.save(event);
        return event;
    }

    @Override
    public void delete(String tenantId, String id) {
        Event event = findBy(tenantId, id);
        if (event != null) {
            dynamo.delete(event);
        }
    }

    @Override
    public void disableById(String tenantId, String id) {
        Event event = findBy(tenantId, id);
        if (event != null) {
            event.setActive(false);
            dynamo.save(event);
        }
    }

    @Override
    public List<SearchItem> search(String tenantId) {
        Event hashKey = Event.builder().tenantId(tenantId).build();
        DynamoDBQueryExpression<Event> queryExpression = new DynamoDBQueryExpression<Event>()
                .withHashKeyValues(hashKey);
        List<Event> events = dynamo.query(Event.class, queryExpression);
        List<SearchItem> searchItems = new ArrayList<SearchItem>();
        if (events != null) {
            searchItems = events.stream().map(event -> {
                return SearchItem.builder()
                        .activeSince(event.getActiveSince())
                        .headLine(event.getHeadLine())
                        .name(event.getName())
                        .photoUrl(event.getPhotoUrl())
                        .rating(event.getRating())
                        .social(event.getSocial())
                        .build();
            }).collect(Collectors.toList());
        }
        return searchItems;
    }
}
