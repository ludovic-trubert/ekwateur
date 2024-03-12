package ekwateur.model.client;

import lombok.Builder;

@Builder
public record CompanyClient
(
  String reference,
  String siret,
  String companyName,
  double ca
)
implements Client
{}
