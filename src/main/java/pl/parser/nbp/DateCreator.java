package pl.parser.nbp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public enum DateCreator {
    INSTANCE;

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public LocalDate create(String dateAsString) {
        return LocalDate.parse(dateAsString, dateTimeFormatter);
    }
}
