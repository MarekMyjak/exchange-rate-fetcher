package pl.dashboard.nbp;

import org.junit.Test;
import pl.dashboard.nbp.dateValidator.ExchangeRateDateValidator;

import java.util.List;

import static org.junit.Assert.*;

public class ExchangeRateProcessorTest {

    @Test
    public void testValidDate() {
        List<ExchangeRate> exchangeRates = ExchangeRateProcessor.INSTANCE.process("2018-07-02");
        assertNotNull("Check of object is not null.", exchangeRates);
        assertEquals("Check if there is 4 elements in list.", 4, exchangeRates.size());
        assertTrue("Check if all currency code is valid.", exchangeRates.stream()
                .allMatch(ExchangeRate::isCodeValid));
    }

    @Test
    public void testInvalidDate() {
        assertNull(ExchangeRateProcessor.INSTANCE.process("4234234234"));
    }

}
