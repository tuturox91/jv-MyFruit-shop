package core.basesyntax.service.operations;

import core.basesyntax.FruitTransaction;
import core.basesyntax.db.ShopStorage;

public class ReturnOperation implements Operation {

    @Override
    public void doWork(FruitTransaction fruitTransaction) {
        int fruitCount = ShopStorage.storageItems.getOrDefault(fruitTransaction.getFruitName(), 0);
        ShopStorage.storageItems.put(fruitTransaction.getFruitName(), fruitCount += fruitTransaction.getQuantity());
    }
}
