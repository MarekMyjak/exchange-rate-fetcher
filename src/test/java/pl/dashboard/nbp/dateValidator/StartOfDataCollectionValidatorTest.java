package pl.dashboard.nbp.dateValidator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class StartOfDataCollectionValidatorTest {

    @Test
    public void date2001ShouldBeInvalid() {
        String correctDate = "2001-07-02";
        assertFalse(DateFormatValidator.INSTANCE.isInvalid(correctDate));
    }

    @Test
    public void date1000ShouldBeInvalid() {
        String correctDate = "1000-07-02";
        assertFalse(DateFormatValidator.INSTANCE.isInvalid(correctDate));
    }
}
