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
public class UpdateCommand extends Command {
    private String code;
    private String name;
    private String eventDate;
    private Schedule schedule;
    private String venue;
    private String imageUrl;
    private String summary;
    private String description;
    private String registrationUrl;
    private String eventRecordingUrl;
    private Promotion promotion;
    private Boolean pastPopularEvent;
    private Region region;
}
