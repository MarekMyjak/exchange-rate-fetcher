package pl.dashboard.nbp.dateValidator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateFormatValidatorTest {

    @Test
    public void correctDateShouldBeValid() {
        String correctDate = "2018-07-02";
        assertFalse(DateFormatValidator.INSTANCE.isInvalid(correctDate));
    }

    @Test
    public void incorrectDateShouldBeInvalid() {
        String inValidDate = "2018-07-32";
        assertTrue(DateFormatValidator.INSTANCE.isInvalid(inValidDate));
    }

    @Test
    public void otherStringThanDateShouldBeInvalid() {
        String inValidDate = "aaaa";
        assertTrue(DateFormatValidator.INSTANCE.isInvalid(inValidDate));
    }
}
