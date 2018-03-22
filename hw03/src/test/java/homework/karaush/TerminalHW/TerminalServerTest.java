package homework.karaush.TerminalHW;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TerminalServerTest {


    @Test
    public void testInitialValue() {
        TerminalServer terminalServer = new TerminalServer();
        assertTrue("must be zero", terminalServer.getAccountMoney() == 0);
    }

    @Test
    public void shouldPutMoney() {
        TerminalServer terminalServer = new TerminalServer();
        terminalServer.putMoney(200);
        assertTrue("must be increased", terminalServer.getAccountMoney() == 200);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void negativeAmount() {
        TerminalServer terminalServer = new TerminalServer();
        terminalServer.putMoney(-200);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void notEvenAmount() {
        TerminalServer terminalServer = new TerminalServer();
        terminalServer.putMoney(175);
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void notEnoughMoney() {
        TerminalServer terminalServer = new TerminalServer();
        terminalServer.putMoney(200);
        terminalServer.withdrawMoney(300);
    }

    @Test
    public void multipleDepositsAndWithdrawals() {
        TerminalServer terminalServer = new TerminalServer();
        terminalServer.putMoney(400);
        terminalServer.withdrawMoney(100);
        terminalServer.putMoney(300);
        terminalServer.withdrawMoney(200);
        terminalServer.putMoney(300);
        terminalServer.withdrawMoney(400);
        terminalServer.putMoney(500);
        terminalServer.withdrawMoney(100);

        assertTrue(terminalServer.getAccountMoney() == 700);
    }
}