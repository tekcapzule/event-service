package com.tekcapsule.event.application.function.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.event.domain.model.Schedule;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class CreateInput {
    private String name;
    private String eventDate;
    private Schedule schedule;
    private String venue;
    private String imageUrl;
    private String description;
    private String registrationUrl;
}