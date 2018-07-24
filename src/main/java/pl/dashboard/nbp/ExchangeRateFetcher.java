package pl.dashboard.nbp;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import lombok.SneakyThrows;
import org.apache.commons.text.TextStringBuilder;
import org.apache.http.HttpStatus;
import org.json.JSONArray;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

enum ExchangeRateFetcher {
    INSTANCE;

    private static final String URL = "http://api.nbp.pl/api/exchangerates/tables/c/{date}/?format=json";
    private static final String ROUTE_PARAM_NAME = "date";
    private static final String UNKNOWN_ERROR_MESSAGE = "Something goes wrong.";
    private static final String CAUSE_FORMAT = "Cause: %s";

    @SneakyThrows
    List<ExchangeRate> fetch(String date) {
        HttpResponse<String> response = Unirest.get(URL)
                .routeParam(ROUTE_PARAM_NAME, date)
                .header(ACCEPT, APPLICATION_JSON.getMimeType())
                .asString();

        if (HttpStatus.SC_OK == response.getStatus()) {
            JSONArray rates = new JSONArray(response.getBody()).getJSONObject(0).getJSONArray("rates");
            List<ExchangeRate> exchangeRates = JsonArrayToListMapper.INSTANCE.map(rates);
            return exchangeRates.stream().filter(ExchangeRate::isCodeValid).collect(Collectors.toList());
        }

        return throwException(response.getBody());
    }

    private List<ExchangeRate> throwException(String body) {
        String errorMessage = new TextStringBuilder()
                .appendln(UNKNOWN_ERROR_MESSAGE)
                .appendln(CAUSE_FORMAT, body)
                .build();
        throw new IllegalStateException(errorMessage);
    }
}
