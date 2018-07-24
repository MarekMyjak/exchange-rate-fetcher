package pl.dashboard.nbp.dateValidator;

import java.util.stream.Stream;

public enum ExchangeRateDateValidator {
    INSTANCE;

    public boolean isInValid(String date) {
        if (isInValid(DateFormatValidator.INSTANCE, date)) {
            return true;
        }
        return Stream.of(StartOfDataCollectionValidator.INSTANCE,
                FutureDateValidator.INSTANCE,
                DayOfWeekendValidator.INSTANCE)
                .anyMatch(validator -> isInValid(validator, date));
    }

    private boolean isInValid(DateValidator dateValidator, String date) {
        boolean invalid = dateValidator.isInvalid(date);
        if (invalid) {
            System.out.println(dateValidator.getErrorMessage(date));
        }
        return invalid;
    }
}
