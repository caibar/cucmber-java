package hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import nicebank.Account;
import nicebank.AtmServer;
import nicebank.CashSlot;
import support.TestCashSlot;


public class ServerHooks {
    public static final int PORT = 8887;

    private AtmServer server;
    private Account account;
    private TestCashSlot cashSlot;

    public ServerHooks(Account account, TestCashSlot cashSlot) {
        this.account = account;
        this.cashSlot = cashSlot;
    }

    @Before
    public void startServer() throws Exception {
        server = new AtmServer(PORT, cashSlot, account);
        server.start();
    }

    @After
    public void stopServer() throws Exception {
        server.stop();
    }
}
