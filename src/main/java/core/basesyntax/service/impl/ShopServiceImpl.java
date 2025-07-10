package core.basesyntax.service.impl;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.ShopService;
import core.basesyntax.strategy.OperationHandler;
import core.basesyntax.strategy.OperationStrategy;
import java.util.List;

public class ShopServiceImpl implements ShopService {
    private final OperationStrategy operationStrategy;

    public ShopServiceImpl(OperationStrategy operationStrategy) {
        this.operationStrategy = operationStrategy;
    }

    @Override
    public void process(List<FruitTransaction> transactions) {
        if (transactions == null || transactions.isEmpty()) {
            throw new IllegalArgumentException("Transactions list cannot be null or empty");
        }
        for (FruitTransaction transaction : transactions) {
            OperationHandler operationHandler
                    = operationStrategy.getOperationHandler(transaction.getOperation());

            if (operationHandler == null) {
                throw new IllegalArgumentException("Can't find handler for operation "
                        + transaction.getOperation());
            }
            operationHandler.handle(transaction);
        }
    }
}
