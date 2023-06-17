package com.tekcapsule.event.domain.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.core.domain.AggregateRoot;
import com.tekcapsule.core.domain.BaseDomainEntity;
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
    @DynamoDBAttribute(attributeName = "name")
    private String name;
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
    @DynamoDBAttribute(attributeName = "eventRecordingUrl")
    private String eventRecordingUrl;
    @DynamoDBAttribute(attributeName = "promoted")
    private Boolean promoted;
    @DynamoDBAttribute(attributeName = "pastPopularEvent")
    private Boolean pastPopularEvent;
    @DynamoDBAttribute(attributeName = "region")
    @DynamoDBTypeConvertedEnum
    private Region region;
    @DynamoDBAttribute(attributeName = "status")
    @DynamoDBTypeConvertedEnum
    private Status status;

}