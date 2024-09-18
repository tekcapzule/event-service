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
public class UpdateCommand extends Command {
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
    private String organizer;
    private Feedback feedback;
}
