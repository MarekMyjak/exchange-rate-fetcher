package pl.parser.nbp;

import lombok.Getter;
import pl.parser.nbp.dateValidator.ExchangeRateDateValidator;

import java.time.LocalDate;

@Getter
class InputData {
    private final String code;
    private final LocalDate startData;
    private final LocalDate endDate;

    InputData(String code, String startDate, String endDate) {
        this.code = code;

        validateDate(startDate, endDate);
        this.startData = DateCreator.INSTANCE.create(startDate);
        this.endDate = DateCreator.INSTANCE.create(endDate);
        validateDateOrder();
    }

    private void validateDate(String startDate, String endDate) {
        if (ExchangeRateDateValidator.INSTANCE.isInValid(startDate) || ExchangeRateDateValidator.INSTANCE.isInValid(endDate)) {
            throw new IllegalArgumentException("Invalid date");
        }
    }

    private void validateDateOrder() {
        if (this.startData.isAfter(this.endDate)) {
            throw new IllegalArgumentException("Start date is after end date.");
        }
    }
}
