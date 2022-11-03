package core.basesyntax.service.operations;

import core.basesyntax.FruitTransaction;
import core.basesyntax.db.ShopStorage;

public class BalanceOperation implements Operation{

    @Override
    public void doWork(FruitTransaction fruitTransaction) {
        ShopStorage.storageItems.put(fruitTransaction.getFruitName(), fruitTransaction.getQuantity());
    }
}
