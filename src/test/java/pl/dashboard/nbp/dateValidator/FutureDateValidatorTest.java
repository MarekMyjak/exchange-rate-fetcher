package pl.dashboard.nbp.dateValidator;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FutureDateValidatorTest {

    @Test
    public void futureDateShouldBeInvalid() {
        String correctDate = "3018-07-02";
        assertTrue(FutureDateValidator.INSTANCE.isInvalid(correctDate));
    }

}
