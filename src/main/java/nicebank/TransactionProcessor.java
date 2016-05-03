package nicebank;

public class TransactionProcessor {
    private TransactionQueue queue = new TransactionQueue();

    public void process() {
        do {
            String message = queue.read();

            if (message.length() > 0) {
                Money balance = BalanceStore.getBalance();
                Money transactionAmount = new Money(message);

                if (isCreditTransaction(message)) {
                    BalanceStore.setBalance(balance.add(transactionAmount));
                } else {
                    BalanceStore.setBalance(balance.minus(transactionAmount));
                }
            }
        } while (true);
    }

    private boolean isCreditTransaction(String message) {
        return !message.startsWith("-");
    }
}
