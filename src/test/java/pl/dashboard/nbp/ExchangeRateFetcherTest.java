package pl.dashboard.nbp;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.*;

public class ExchangeRateFetcherTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void checkIfAllElementsContainsValidCurrencyCode() {
        List<ExchangeRate> exchangeRates = ExchangeRateFetcher.INSTANCE.fetch("2018-02-27");
        assertNotNull("Check of object is not null.", exchangeRates);
        assertEquals("Check if there is 4 elements in list.", 4, exchangeRates.size());
        assertTrue("Check if all currency code is valid.", exchangeRates.stream()
                .allMatch(ExchangeRate::isCodeValid));
    }


    @Test
    public void checkIncorrectState() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Something goes wrong.\n" +
                "Cause: 400 BadRequest - Błędny zakres dat / Invalid date range");

        ExchangeRateFetcher.INSTANCE.fetch("2222-02-27");
    }
}
