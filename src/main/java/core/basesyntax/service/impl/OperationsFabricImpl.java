package core.basesyntax.service.impl;

import core.basesyntax.db.ShopStorageDao;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.OperationsFabric;
import core.basesyntax.service.operations.*;

public class OperationsFabricImpl implements OperationsFabric {
    FruitTransaction transaction;

    public OperationsFabricImpl(FruitTransaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public Operation getOperation() {
        FruitTransaction.OperationType operation = transaction.getOperation();
        switch (operation) {
            case BALANCE -> {
                return new BalanceOperation();
            }
            case SUPPLY -> {
                return new SupplyOperation();
            }
            case PURCHASE -> {
                return new PurchaseOperation();
            }
            case RETURN -> {
                return new ReturnOperation();
            }
        }
        return null;
    }
}
