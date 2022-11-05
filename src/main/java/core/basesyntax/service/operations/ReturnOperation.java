package core.basesyntax.service.operations;

import core.basesyntax.db.ShopStorageDao;
import core.basesyntax.model.FruitTransaction;

public class ReturnOperation implements Operation {

    @Override
    public void doWork(FruitTransaction fruitTransaction, ShopStorageDao shopStorageDao) {
        String itemName = fruitTransaction.getFruitName();
        int itemCount = shopStorageDao.getItemCount(itemName);
        shopStorageDao.addOrReplace(itemName, itemCount + fruitTransaction.getQuantity());
    }
}
