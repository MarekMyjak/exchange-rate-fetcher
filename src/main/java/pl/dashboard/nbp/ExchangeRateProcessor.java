package pl.dashboard.nbp;

import pl.dashboard.nbp.dateValidator.ExchangeRateDateValidator;

import java.util.List;

enum ExchangeRateProcessor {
    INSTANCE;

    public List<ExchangeRate> process(String date) {
        if (ExchangeRateDateValidator.INSTANCE.isInValid(date)) {
            return null;
        }
        List<ExchangeRate> exchangeRates = ExchangeRateFetcher.INSTANCE.fetch(date);
        if (exchangeRates != null) {
            ExchangeRatePrinter.INSTANCE.print(exchangeRates, date);
        }
        return exchangeRates;
    }
}
