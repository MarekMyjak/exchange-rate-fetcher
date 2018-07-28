package pl.parser.nbp.dateValidator;

interface DateValidator {
    String DATE_FORMAT = "yyyy-MM-dd";

    boolean isInvalid(String dateAsString);
    String getErrorMessage(String dateAsString);
}
