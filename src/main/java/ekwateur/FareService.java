package ekwateur;

import ekwateur.model.Fare;
import ekwateur.model.client.Client;
import ekwateur.model.client.CompanyClient;
import ekwateur.model.client.IndividualClient;
import java.time.YearMonth;

public class FareService
{
  public Fare findFare(Client client, YearMonth yearMonth)
  {
    return switch (client)
    {
      case IndividualClient c -> this.findFare(c, yearMonth);
      case CompanyClient c -> this.findFare(c, yearMonth);
      default -> throw new IllegalArgumentException();
    };
  }

  private Fare findFare(IndividualClient client, YearMonth yearMonth)
  {
    return Fare.builder()
      .electricity(0.121f)
      .gas(0.115f)
      .build();
  }

  private Fare findFare(CompanyClient client, YearMonth yearMonth)
  {
    if (client.ca() > 1_000_000)
    {
      return Fare.builder()
        .electricity(0.114f)
        .gas(0.111f)
        .build();
    }
    return Fare.builder()
      .electricity(0.118f)
      .gas(0.113f)
      .build();
  }
}
