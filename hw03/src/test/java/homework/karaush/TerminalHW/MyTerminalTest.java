package homework.karaush.TerminalHW;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyTerminalTest {


    @Test
    public void validatePin() {
        MyTerminal myTerminal = new MyTerminal();
        assertTrue("pin validation ", myTerminal.validatePin("1234"));
    }

    @Test
    public void checkBalance() {
        MyTerminal myTerminal = new MyTerminal();
        assertTrue(myTerminal.checkBalance() == 0);

        myTerminal.putMoney(500);
        assertTrue(myTerminal.checkBalance() == 500);
    }


    @Test
    public void withdrawMoney() {
        MyTerminal myTerminal = new MyTerminal();

        myTerminal.putMoney(500);
        myTerminal.withdrawMoney(400);

        assertTrue(myTerminal.checkBalance() == 100);


    }

}