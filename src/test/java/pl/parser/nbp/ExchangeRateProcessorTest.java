package pl.parser.nbp;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ExchangeRateProcessorTest {

    @Rule
    public final ExpectedException expectedException  = ExpectedException.none();

    @Test
    public void testSmallPeriod() {
        ExchangeRates exchangeRates = ExchangeRateProcessor.INSTANCE.process(new InputData("EUR","2013-01-28", "2013-01-31"));

        assertEquals("Check average bid price.", Double.parseDouble("4.1505"), exchangeRates.calculateAverageBidPrice(), 0.001);
        assertEquals("Check standard deviation ask price.", Double.parseDouble("0.0144 "), exchangeRates.calculateStandardDeviatonOfAskPrice(), 0.001);
    }

    @Test
    public void testLongPeriod() {
        ExchangeRates exchangeRates = ExchangeRateProcessor.INSTANCE.process(new InputData("EUR","2013-12-28", "2015-01-02"));

        assertEquals("Check average bid price.", Double.parseDouble("4.1416"), exchangeRates.calculateAverageBidPrice(), 0.001);
        assertEquals("Check standard deviation ask price.", Double.parseDouble("0.0335 "), exchangeRates.calculateStandardDeviatonOfAskPrice(), 0.001);
    }

    @Test
    public void testInvalidDate() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid date");

        ExchangeRateProcessor.INSTANCE.process(new InputData("EUR","2013-01-01", "2013-01-00"));
    }

}
