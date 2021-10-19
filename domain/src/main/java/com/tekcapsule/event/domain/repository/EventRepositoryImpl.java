package com.tekcapsule.event.domain.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.tekcapsule.event.domain.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class EventRepositoryImpl implements EventDynamoRepository {

    private DynamoDBMapper dynamo;

    @Autowired
    public EventRepositoryImpl(DynamoDBMapper dynamo) {
        this.dynamo = dynamo;
    }

    @Override
    public List<Event> findAll() {
        return dynamo.scan(Event.class,new DynamoDBScanExpression());
    }

    @Override
    public Event findBy(String code) {
        return dynamo.load(Event.class, code);
    }

    @Override
    public Event save(Event event) {
        dynamo.save(event);
        return event;
    }

    @Override
    public void disable(String code) {
        Event event = findBy(code);
        if (event != null) {
            event.setActive(false);
            dynamo.save(event);
        }
    }
}
