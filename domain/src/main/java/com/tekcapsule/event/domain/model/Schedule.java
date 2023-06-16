package com.tekcapsule.event.domain.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument
public class Schedule {
    @DynamoDBAttribute(attributeName ="startDate")
    private String startDate;
    @DynamoDBAttribute(attributeName ="endDate")
    private String endDate;
    @DynamoDBAttribute(attributeName ="startTime")
    private String startTime;
    @DynamoDBAttribute(attributeName ="endTime")
    private String endTime;
}
