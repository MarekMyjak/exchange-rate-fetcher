package pl.dashboard.nbp.dateValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


enum FutureDateValidator implements DateValidator {
    INSTANCE;

    private static final String ERROR_MESSAGE = "This date %s is from future. NBP does not publish future exchange rates.";

    @Override
    public boolean isInvalid(String dateAsString) {
        return isFromFuture(dateAsString);
    }

    @Override
    public String getErrorMessage(String dateAsString) {
        return String.format(ERROR_MESSAGE, dateAsString);
    }

    private boolean isFromFuture(String dateAsString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate date = LocalDate.parse(dateAsString, formatter);
        return date.isAfter(LocalDate.now());
    }
}
