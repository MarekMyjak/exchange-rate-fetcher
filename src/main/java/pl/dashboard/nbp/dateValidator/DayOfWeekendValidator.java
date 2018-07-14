package pl.dashboard.nbp.dateValidator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

enum DayOfWeekendValidator implements DateValidator {
    INSTANCE;

    private static final String ERROR_MESSAGE = "This date %s falls on the weekend. NBP does not publish exchange rates in Saturday and Sunday.";

    @Override
    public boolean isInvalid(String dateAsString) {
        return isWeekend(dateAsString);
    }

    @Override
    public String getErrorMessage(String dateAsString) {
        return String.format(ERROR_MESSAGE, dateAsString);
    }

    private boolean isWeekend(String dateAsString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate date = LocalDate.parse(dateAsString, formatter);
        DayOfWeek dow = date.getDayOfWeek();
        return DayOfWeek.SATURDAY.equals(dow) || DayOfWeek.SUNDAY.equals(dow);
    }
}
