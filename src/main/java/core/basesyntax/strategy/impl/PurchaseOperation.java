package core.basesyntax.strategy.impl;

import core.basesyntax.dao.FruitDao;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.OperationHandler;

public class PurchaseOperation implements OperationHandler {
    private final FruitDao fruitDao;

    public PurchaseOperation(FruitDao fruitDao) {
        this.fruitDao = fruitDao;
    }

    @Override
    public void handle(FruitTransaction transaction) {
        String fruit = transaction.getFruit();
        int quantityToPurchase = transaction.getQuantity();
        int currentFruitQuantity = fruitDao.get(fruit);

        if (currentFruitQuantity < quantityToPurchase) {
            throw new RuntimeException("Not enough fruit to purchase");
        }
        fruitDao.update(fruit, currentFruitQuantity - quantityToPurchase);
    }
}
