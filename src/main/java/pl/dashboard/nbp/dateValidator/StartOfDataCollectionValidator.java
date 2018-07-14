package pl.dashboard.nbp.dateValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

enum StartOfDataCollectionValidator implements DateValidator {
    INSTANCE;

    private static final String ERROR_MESSAGE = "This date %s is from past. NBP does not publish exchange rates before 2002.";
    private static final String START_OF_DATA_COLLECTION_DATE = "2002-01-01";

    @Override
    public boolean isInvalid(String dateAsString) {
        return isBefore2002(dateAsString);
    }

    @Override
    public String getErrorMessage(String dateAsString) {
        return String.format(ERROR_MESSAGE, dateAsString);
    }

    private boolean isBefore2002(String dateAsString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate date = LocalDate.parse(dateAsString, formatter);
        LocalDate startDate = LocalDate.parse(START_OF_DATA_COLLECTION_DATE, formatter);
        return date.isBefore(startDate);
    }
}
