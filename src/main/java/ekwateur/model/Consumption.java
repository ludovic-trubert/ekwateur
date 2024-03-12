package ekwateur.model;

import lombok.Builder;

@Builder
public record Consumption
(
  int electricity,
  int gaz
)
{}
