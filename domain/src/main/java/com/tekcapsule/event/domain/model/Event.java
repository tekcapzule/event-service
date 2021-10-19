package com.tekcapsule.event.domain.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
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
public class Event extends BaseDomainEntity<String> implements AggregateRoot {

    @DynamoDBHashKey(attributeName="code")
    private String code;
    @DynamoDBRangeKey(attributeName="eventDate")
    private String eventDate;
    @DynamoDBAttribute(attributeName = "name")
    private String name;
    @DynamoDBAttribute(attributeName = "imageUrl")
    private String imageUrl;
    @DynamoDBAttribute(attributeName = "description")
    private String description;
    @DynamoDBAttribute(attributeName = "registrationUrl")
    private String registrationUrl;
    @DynamoDBAttribute(attributeName="active")
    private Boolean active;

}