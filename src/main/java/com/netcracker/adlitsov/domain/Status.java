package com.netcracker.adlitsov.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    OK, FAIL;

    private String description;

    public Status setDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonValue
    public String getDescription() {
        return this.name() + ": " + description;
    }
}
