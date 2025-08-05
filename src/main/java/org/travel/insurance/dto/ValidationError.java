package org.travel.insurance.dto;

public record ValidationError(
        String errorCode,
        String description
) {}
