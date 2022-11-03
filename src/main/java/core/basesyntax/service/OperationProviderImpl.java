package core.basesyntax.service;

import core.basesyntax.FruitTransaction;
import core.basesyntax.service.operations.*;

public class OperationProviderImpl implements OperationProvider {

    FruitTransaction transaction;

    public OperationProviderImpl(FruitTransaction transaction) {
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
