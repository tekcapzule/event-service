package com.tekcapzule.event.domain.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapzule.core.domain.AggregateRoot;
import com.tekcapzule.core.domain.BaseDomainEntity;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@DynamoDBTable(tableName = "Event")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event extends BaseDomainEntity implements AggregateRoot {

    @DynamoDBHashKey(attributeName="code")
    private String code;
    @DynamoDBAttribute(attributeName="eventDate")
    private String eventDate;
    @DynamoDBAttribute(attributeName="schedule")
    private Schedule schedule;
    @DynamoDBAttribute(attributeName = "title")
    private String title;
    @DynamoDBAttribute(attributeName = "venue")
    private String venue;
    @DynamoDBAttribute(attributeName = "imageUrl")
    private String imageUrl;
    @DynamoDBAttribute(attributeName = "summary")
    private String summary;
    @DynamoDBAttribute(attributeName = "description")
    private String description;
    @DynamoDBAttribute(attributeName = "registrationUrl")
    private String registrationUrl;
    @DynamoDBAttribute(attributeName = "resourceUrl")
    private String resourceUrl;
    @DynamoDBAttribute(attributeName = "organizer")
    private String organizer;
    @DynamoDBAttribute(attributeName = "promotion")
    private Promotion promotion;
    @DynamoDBAttribute(attributeName = "feedback")
    private Feedback feedback;
    @DynamoDBAttribute(attributeName = "pastPopularEvent")
    private Boolean pastPopularEvent;
    @DynamoDBAttribute(attributeName = "region")
    @DynamoDBTypeConvertedEnum
    private Region region;
    @DynamoDBAttribute(attributeName = "status")
    @DynamoDBTypeConvertedEnum
    private Status status;

}