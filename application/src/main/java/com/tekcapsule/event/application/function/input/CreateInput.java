package com.tekcapsule.event.application.function.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.event.domain.model.Promotion;
import com.tekcapsule.event.domain.model.Region;
import com.tekcapsule.event.domain.model.Schedule;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class CreateInput {
    private String title;
    private String eventDate;
    private Schedule schedule;
    private String venue;
    private String imageUrl;
    private String summary;
    private String description;
    private String registrationUrl;
    private Promotion promotion;
    private Region region;
    private Boolean pastPopularEvent;
    private String resourceUrl;
}