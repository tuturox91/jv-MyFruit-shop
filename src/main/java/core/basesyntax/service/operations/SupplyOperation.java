package core.basesyntax.service.operations;

import core.basesyntax.db.ShopStorageDao;
import core.basesyntax.model.FruitTransaction;

public class SupplyOperation implements Operation{
    @Override
    public void doWork(FruitTransaction fruitTransaction, ShopStorageDao shopStorageDao) {
        shopStorageDao.addOrReplace(fruitTransaction.getFruitName(), fruitTransaction.getQuantity());
    }
}
