package pl.parser.nbp;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ExchangeRateFetcherTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void checkIfAllElementsContainsValidCurrencyCode() {
        List<String> xmlFileNames = Arrays.asList("c019z130128", "c020z130129", "c021z130130", "c022z130131");
        ExchangeRates exchangeRates = ExchangeRateFetcher.INSTANCE.fetch(xmlFileNames, "EUR");
        assertNotNull("Check of object is not null.", exchangeRates);
        assertEquals("Check average bid price.", Double.parseDouble("4.1505"), exchangeRates.calculateAverageBidPrice(), 0.001);
        assertEquals("Check standard deviation ask price.", Double.parseDouble("0.0144 "), exchangeRates.calculateStandardDeviatonOfAskPrice(), 0.001);
    }


    @Test
    public void checkIncorrectState() {
        List<String> xmlFileNames = Collections.singletonList("c019z130101");
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Something goes wrong.");

        ExchangeRateFetcher.INSTANCE.fetch(xmlFileNames, "EUR");
    }
}
