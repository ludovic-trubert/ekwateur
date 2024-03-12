package ekwateur;

import static org.assertj.core.api.Assertions.assertThat;

import ekwateur.model.client.Client;
import ekwateur.model.client.CompanyClient;
import ekwateur.model.client.IndividualClient;
import java.time.YearMonth;
import org.junit.jupiter.api.Test;

class FareServiceTest
{
  // Conso mockées dans le service
  private static final int CONSO_ELEC = 1000;
  private static final int CONSO_GAS = 100;

  private final PriceService priceService = new PriceService();

  /*
   * Pour les particuliers, le prix du kWh est
   * de 0,121 € pour l'électricité
   * et 0,115€ pour le gaz
   */
  @Test
  void computePrice_individual()
  {
    // given
    Client client = IndividualClient.builder()
      .reference("EKW00000001")
      .build();
    YearMonth yearMonth = YearMonth.of(2024, 1);

    // when
    float price = priceService.computePrice(client, yearMonth);

    // then
    assertThat(price).isEqualTo(0.121f * CONSO_ELEC + 0.115f * CONSO_GAS);
  }

  /*
   * Pour les pro, ayant un CA supérieur à 1 000 000 €, le prix du kWh est
   * de 0,114 € pour l'électricité
   * et 0,111€ pour le gaz
   */
  @Test
  void computePrice_companyHighCA()
  {
    // given
    Client client = CompanyClient.builder()
      .reference("EKW00000002")
      .ca(1_500_000)
      .build();
    YearMonth yearMonth = YearMonth.of(2024, 1);

    // when
    float price = priceService.computePrice(client, yearMonth);

    // then
    assertThat(price).isEqualTo(0.114f * CONSO_ELEC + 0.111f * CONSO_GAS);
  }

  /*
   * Pour les pro, ayant un CA inférieur à 1 000 000 €, le prix du kWh est
   * de 0,118 € pour l'électricité
   * et 0,113€ pour le gaz
   */
  @Test
  void computePrice_companyLowCA()
  {
    // given
    Client client = CompanyClient.builder()
      .reference("EKW00000003")
      .ca(500_000)
      .build();
    YearMonth yearMonth = YearMonth.of(2024, 1);

    // when
    float price = priceService.computePrice(client, yearMonth);

    // then
    assertThat(price).isEqualTo(0.118f * CONSO_ELEC + 0.113f * CONSO_GAS);
  }
}
