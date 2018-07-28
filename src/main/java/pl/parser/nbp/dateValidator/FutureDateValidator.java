package pl.parser.nbp.dateValidator;

import pl.parser.nbp.DateCreator;

import java.time.LocalDate;


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
        LocalDate date = DateCreator.INSTANCE.create(dateAsString);
        return date.isAfter(LocalDate.now());
    }
}
