package dto;

import lombok.Builder;

@Builder
public record FraudCheckDto(Boolean isFraudster) {
}
