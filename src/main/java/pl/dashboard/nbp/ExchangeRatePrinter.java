package pl.dashboard.nbp;

import org.apache.commons.text.TextStringBuilder;

import java.util.List;

enum ExchangeRatePrinter {
    INSTANCE;

    String print(List<ExchangeRate> exchangeRates, String date) {
        TextStringBuilder stringBuilder = prepareHeading(date);
        appendExchangeRates(exchangeRates, stringBuilder);
        String result = stringBuilder.build();
        System.out.println(result);
        return result;
    }

    private TextStringBuilder prepareHeading(String date) {
        return new TextStringBuilder()
                .appendln("Data: %s", date)
                .appendln("Waluta = kupno; sprzedaż");
    }

    private void appendExchangeRates(List<ExchangeRate> exchangeRates, TextStringBuilder stringBuilder) {
        exchangeRates.forEach(stringBuilder::appendln);
    }
}
