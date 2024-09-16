package com.tekcapzule.event.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapzule.core.domain.Command;
import com.tekcapzule.event.domain.model.Feedback;
import com.tekcapzule.event.domain.model.Promotion;
import com.tekcapzule.event.domain.model.Region;
import com.tekcapzule.event.domain.model.Schedule;
import lombok.Builder;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class CreateCommand extends Command {
    private String title;
    private String eventDate;
    private String organizer;
    private Feedback feedback;
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



