package ekwateur;

import ekwateur.model.Consumption;
import ekwateur.model.Fare;
import ekwateur.model.client.Client;
import java.time.YearMonth;

public class PriceService
{
  private final FareService fareService = new FareService();

  public float computePrice(Client client, YearMonth yearMonth)
  {
    Fare fare = fareService.findFare(client, yearMonth);
    Consumption consumption = this.getConsumption(client, yearMonth);

    float electricityPrice = consumption.electricity() * fare.electricity();
    float gasPrice = consumption.gaz() * fare.gas();
    float totalPrice = electricityPrice + gasPrice;

    System.out.printf("Price for client %s: %s", client.reference(), totalPrice);

    return totalPrice;
  }

  private Consumption getConsumption(Client client, YearMonth yearMonth)
  {
    // mock infra (DB)
    return Consumption.builder()
      .electricity(1000)
      .gaz(100)
      .build();
  }
}
