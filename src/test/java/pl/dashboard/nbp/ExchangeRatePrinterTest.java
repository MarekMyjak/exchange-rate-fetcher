package pl.dashboard.nbp;

import org.junit.Test;

import java.math.BigDecimal;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class ExchangeRatePrinterTest {

    private static final String SINGLE_ELEMENT_EXPECTED_RESULT = "Data: 2017-02-12\n" +
            "Waluta = kupno; sprzedaż\n" +
            "A = 99.9; 999.99\n";
    private static final String MULTIPLE_ELEMENT_EXPECTED_RESULT = "Data: 2017-02-12\n" +
            "Waluta = kupno; sprzedaż\n" +
            "A = 99.9; 999.99\n" +
            "B = 199.9; 1999.99\n" +
            "C = 299.9; 2999.99\n";

    @Test
    public void checkPrinter() {
        ExchangeRate exchangeRate = new ExchangeRate("A", new BigDecimal("99.9"), new BigDecimal("999.99"));
        String actualResult = ExchangeRatePrinter.INSTANCE.print(singletonList(exchangeRate), "2017-02-12");
        assertEquals(SINGLE_ELEMENT_EXPECTED_RESULT
                , actualResult);
    }

    @Test
    public void checkPrinterForMultipleElements() {
        ExchangeRate exchangeRate = new ExchangeRate("A", new BigDecimal("99.9"), new BigDecimal("999.99"));
        ExchangeRate exchangeRate2 = new ExchangeRate("B", new BigDecimal("199.9"), new BigDecimal("1999.99"));
        ExchangeRate exchangeRate3 = new ExchangeRate("C", new BigDecimal("299.9"), new BigDecimal("2999.99"));

        String actualResult = ExchangeRatePrinter.INSTANCE.print(asList(exchangeRate, exchangeRate2, exchangeRate3), "2017-02-12");
        assertEquals(MULTIPLE_ELEMENT_EXPECTED_RESULT
                , actualResult);
    }
}
