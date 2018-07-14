package pl.dashboard.nbp;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.EnumUtils;

@AllArgsConstructor
enum ValidCurrencyCode {
    USD("USD"),
    EUR("EUR"),
    CHF("CHF"),
    GBP("GBP");

    private String value;

    public static boolean contains(String value) {
        return EnumUtils.isValidEnum(ValidCurrencyCode.class, value);
    }
}
