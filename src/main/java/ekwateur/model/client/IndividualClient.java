package ekwateur.model.client;

import lombok.Builder;

@Builder
public record IndividualClient
(
  String reference,
  String civility,
  String firstName,
  String lastName
)
implements Client
{}
