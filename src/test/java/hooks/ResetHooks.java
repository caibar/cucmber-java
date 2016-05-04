package hooks;

import cucumber.api.java.Before;
import nicebank.BalanceStore;
import nicebank.TransactionQueue;

public class ResetHooks {
    @Before(order = 1)
    public void reset() {
        /*if (!Base.hasConnection()) {
            Base.open(
                    "com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost/bank",
                    "teller", "password");
        }
        Account.deleteAll();*/
        TransactionQueue.clear();
        BalanceStore.clear(); //quando trocar por base de dados ele saí fora
    }
}
