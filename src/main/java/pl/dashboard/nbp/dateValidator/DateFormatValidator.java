package pl.dashboard.nbp.dateValidator;

import static java.lang.String.format;
import static org.apache.commons.validator.routines.DateValidator.getInstance;

enum DateFormatValidator implements DateValidator {
    INSTANCE;

    private static final String ERROR_MESSAGE = "Date %s is invalid.";

    @Override
    public boolean isInvalid(String dateAsString) {
        return getInstance().validate(dateAsString, DATE_FORMAT) == null;
    }

    @Override
    public String getErrorMessage(String dateAsString) {
        return format(ERROR_MESSAGE, dateAsString);
    }
}
