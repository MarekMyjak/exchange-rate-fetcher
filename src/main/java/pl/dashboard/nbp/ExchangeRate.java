package pl.dashboard.nbp;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
class ExchangeRate {
    private static final String FORMAT = "%s = %s; %s";
    private String code;
    private BigDecimal bid;
    private BigDecimal ask;

    boolean isCodeValid() {
        return ValidCurrencyCode.contains(code);
    }

    @Override
    public String toString() {
        return String.format(FORMAT, code, bid, ask);
    }
}
