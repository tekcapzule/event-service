package com.tekcapsule.event.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.core.domain.Command;
import com.tekcapsule.event.domain.model.Promotion;
import com.tekcapsule.event.domain.model.Region;
import com.tekcapsule.event.domain.model.Schedule;
import lombok.Builder;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class CreateCommand extends Command {
    private String name;
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



