package com.nicoardizzoli.clients.fraud;

import lombok.Builder;

@Builder
public record FraudCheckDto(Boolean isFraudster) {
}
