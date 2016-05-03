package transforms;

import cucumber.api.Transformer;
import nicebank.Money;

/**
 * Created by caibar on 21/04/2016.
 */
public class MoneyConverter extends Transformer<Money> {

    @Override
    public Money transform(String amount) {
        String[] numbers = amount.split("\\.");

        int dollars = Integer.parseInt(numbers[0]);
        int cents = Integer.parseInt(numbers[1]);

        return new Money(dollars, cents);
    }
}
