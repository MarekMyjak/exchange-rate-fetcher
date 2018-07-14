package pl.dashboard.nbp.dateValidator;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DayOfWeekendValidatorTest {

    @Test
    public void dateFromWeekendShouldBeInvalid() {
        String weekendDate = "2018-07-01";
        assertTrue(DayOfWeekendValidator.INSTANCE.isInvalid(weekendDate));
    }

}
