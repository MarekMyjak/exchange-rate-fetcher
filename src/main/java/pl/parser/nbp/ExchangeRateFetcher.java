package pl.parser.nbp;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.entity.ContentType.TEXT_XML;

@Log4j2
enum ExchangeRateFetcher {
    INSTANCE;

    private static final String URL_PATTERN = "http://www.nbp.pl/kursy/xml/%s.xml";
    private static final String UNKNOWN_ERROR_MESSAGE = "Something goes wrong.";

    ExchangeRates fetch(List<String> xmlFileNames, String code) {
        return new ExchangeRates(xmlFileNames.stream().map(xmlFileName -> {
            try {
                HttpResponse<String> response = Unirest.get(String.format(URL_PATTERN, xmlFileName))
                        .header(ACCEPT, TEXT_XML.getMimeType())
                        .asString();
                if (!(HttpStatus.SC_OK == response.getStatus())) {
                    log.error(String.format("External system returns incorrect HTTP status: %s.", response.getStatus()));
                }
                return XmlMapper.INSTANCE.map(response.getBody(), code);
            } catch (Exception e) {
                throw new IllegalStateException(UNKNOWN_ERROR_MESSAGE);
            }
        }).collect(Collectors.toList()));
    }
}
