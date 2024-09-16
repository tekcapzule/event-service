package com.tekcapzule.event.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Region {
    AMERICAS("Americas"),
    EUROPE("Europe"),
    MIDDLE_EAST_AFRICA("Middle East & Africa"),
    ASIA_ASIAPACIFIC("Asia & Asia Pacific");

    @Getter
    private String value;
}