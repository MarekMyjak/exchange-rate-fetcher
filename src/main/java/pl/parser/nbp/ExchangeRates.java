package pl.parser.nbp;

import lombok.AllArgsConstructor;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import java.util.List;

@AllArgsConstructor
class ExchangeRates {

    private List<ExchangeRate> exchangeRates;

    double calculateAverageBidPrice() {
        return exchangeRates.stream().map(ExchangeRate::getBid)
                .mapToDouble(a -> a)
                .average()
                .orElse(0.0);
    }

    double calculateStandardDeviatonOfAskPrice() {
        return new StandardDeviation().evaluate(exchangeRates
                .stream()
                .map(ExchangeRate::getAsk)
                .mapToDouble(d -> d)
                .toArray());
    }

    @Override
    public String toString() {
        return String.format("%.4f\n%.4f",
                calculateAverageBidPrice(),
                calculateStandardDeviatonOfAskPrice());
    }
}
