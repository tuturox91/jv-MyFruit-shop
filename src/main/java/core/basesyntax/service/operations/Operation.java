package core.basesyntax.service.operations;

import core.basesyntax.db.ShopStorageDao;
import core.basesyntax.model.FruitTransaction;

public interface Operation {
    void doWork (FruitTransaction fruitTransaction, ShopStorageDao dao);
}
