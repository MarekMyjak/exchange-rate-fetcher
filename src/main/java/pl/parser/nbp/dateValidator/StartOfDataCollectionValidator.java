package pl.parser.nbp.dateValidator;

import pl.parser.nbp.DateCreator;

import java.time.LocalDate;

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
        LocalDate date = DateCreator.INSTANCE.create(dateAsString);
        LocalDate startDate = DateCreator.INSTANCE.create(START_OF_DATA_COLLECTION_DATE);
        return date.isBefore(startDate);
    }
}
