package homework.karaush.TerminalHW;

import org.junit.Test;

import static org.junit.Assert.*;

public class PinValidatorTest {

    @Test
    public void validatePin() {
        PinValidator pinValidator = new PinValidator("1122");
        assertTrue("validation of correct pin", pinValidator.validate("1122"));
    }

    @Test(expected = IllegalAccessException.class)
    public void expectExceptionAfterTooManyAttempts(){
        PinValidator pinValidator = new PinValidator("1122");
        pinValidator.validate("1234");
        pinValidator.validate("1234");
        pinValidator.validate("1234");
        pinValidator.validate("1234");
    }


    @Test(expected = IllegalAccessException.class)
    public void unblockAfterBlocking(){
        PinValidator pinValidator = new PinValidator("1122");
        pinValidator.validate("1234");
        pinValidator.validate("1234");
        pinValidator.validate("1234");
        pinValidator.validate("1234");
        try {
            Thread.sleep(PinValidator.getSleepTimeInMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
         assertTrue(pinValidator.validate("1122"));
    }

    @Test(expected = IncorrectPinFormatException.class)
    public void testFormat3Digits(){
        PinValidator pinValidator = new PinValidator("1122");
        pinValidator.validate("123");
    }

    @Test(expected = IncorrectPinFormatException.class)
    public void testFormatMoreDigits(){
        PinValidator pinValidator = new PinValidator("1122");
        pinValidator.validate("41241241");
    }

    @Test(expected = IncorrectPinFormatException.class)
    public void testFormatNotDigits(){
        PinValidator pinValidator = new PinValidator("1122");
        pinValidator.validate("qwed");
    }

}