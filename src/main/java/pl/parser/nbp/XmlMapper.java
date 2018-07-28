package pl.parser.nbp;

import lombok.SneakyThrows;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.stream.IntStream;

enum XmlMapper {
    INSTANCE;

    @SneakyThrows
    public ExchangeRate map(String xmlAsString, String code) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(xmlAsString)));
        document.getDocumentElement().normalize();
        NodeList exchangeRates = document.getElementsByTagName("pozycja");

        return IntStream.range(0, exchangeRates.getLength())
                .mapToObj(exchangeRates::item)
                .filter(Element.class::isInstance)
                .map(Element.class::cast)
                .filter(element -> element.getElementsByTagName("kod_waluty").item(0).getTextContent().equals(code))
                .map(element -> {
                    String bid = element.getElementsByTagName("kurs_kupna").item(0).getTextContent().replace(",", ".");
                    String ask = element.getElementsByTagName("kurs_sprzedazy").item(0).getTextContent().replace(",", ".");
                    return new ExchangeRate(code, Double.parseDouble(bid), Double.parseDouble(ask));
                })
                .findAny()
                .orElseThrow(IllegalStateException::new);
    }
}
