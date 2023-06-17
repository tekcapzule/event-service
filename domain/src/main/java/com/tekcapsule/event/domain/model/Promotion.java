package com.tekcapsule.event.domain.model;

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
public class Promotion {
    private Boolean promoted;
    private String endsOnUtc;
    private String imageUrl;
    private String campaignUrl;
}
