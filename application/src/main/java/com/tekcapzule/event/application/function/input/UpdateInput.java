package com.tekcapzule.event.application.function.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapzule.event.domain.model.Promotion;
import com.tekcapzule.event.domain.model.Region;
import com.tekcapzule.event.domain.model.Schedule;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class UpdateInput {
    private String code;
    private String title;
    private String eventDate;
    private Schedule schedule;
    private String venue;
    private String imageUrl;
    private String summary;
    private String description;
    private String registrationUrl;
    private String resourceUrl;
    private Promotion promotion;
    private Boolean pastPopularEvent;
    private Region region;
}