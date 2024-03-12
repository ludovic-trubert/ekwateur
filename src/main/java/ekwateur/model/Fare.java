package ekwateur.model;

import lombok.Builder;

@Builder
public record Fare
(
  float electricity,
  float gas
)
{}
