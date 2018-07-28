package pl.parser.nbp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class XmlMapperTest {

    private static final String XML_STRING = "<?xml version=\"1.0\" encoding=\"ISO-8859-2\"?>\n" +
            "<tabela_kursow typ=\"C\" uid=\"13c001\">\n" +
            "   <numer_tabeli>001/C/NBP/2013</numer_tabeli>\n" +
            "   <data_notowania>2012-12-31</data_notowania>\n" +
            "   <data_publikacji>2013-01-02</data_publikacji>\n" +
            "   <pozycja>\n" +
            "      <nazwa_waluty>dolar amerykanski</nazwa_waluty>\n" +
            "      <przelicznik>1</przelicznik>\n" +
            "      <kod_waluty>USD</kod_waluty>\n" +
            "      <kurs_kupna>3,0686</kurs_kupna>\n" +
            "      <kurs_sprzedazy>3,1306</kurs_sprzedazy>\n" +
            "   </pozycja>\n" +
            "   <pozycja>\n" +
            "      <nazwa_waluty>dolar australijski</nazwa_waluty>\n" +
            "      <przelicznik>1</przelicznik>\n" +
            "      <kod_waluty>AUD</kod_waluty>\n" +
            "      <kurs_kupna>3,1861</kurs_kupna>\n" +
            "      <kurs_sprzedazy>3,2505</kurs_sprzedazy>\n" +
            "   </pozycja>\n" +
            "</tabela_kursow>\n";

    @Test
    public void testUsd() {
        ExchangeRate exchangeRate = XmlMapper.INSTANCE.map(XML_STRING, "USD");
        assertEquals("USD", exchangeRate.getCode());
        assertEquals(Double.parseDouble("3.0686"), exchangeRate.getBid(), 0.001);
        assertEquals(Double.parseDouble("3.1306"), exchangeRate.getAsk(), 0.001);
    }

    @Test
    public void testAud() {
        ExchangeRate exchangeRate = XmlMapper.INSTANCE.map(XML_STRING, "AUD");
        assertEquals("AUD", exchangeRate.getCode());
        assertEquals(Double.parseDouble("3.1861"), exchangeRate.getBid(), 0.001);
        assertEquals(Double.parseDouble("3.2505"), exchangeRate.getAsk(), 0.001);
    }
}
