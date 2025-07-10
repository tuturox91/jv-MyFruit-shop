package core.basesyntax.service.impl;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.DataConverter;
import java.util.ArrayList;
import java.util.List;

public class DataConverterImpl implements DataConverter {
    private static final int FIRST_DATA_LINE = 1;
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int OPERATION_FRUIT_INDEX = 1;
    private static final int OPERATION_QUANTITY_INDEX = 2;
    private static final String SPLITERATOR = ",";

    @Override
    public List<FruitTransaction> convertToTransaction(List<String> inputReport) {
        List<FruitTransaction> transactions = new ArrayList<>();
        for (int i = FIRST_DATA_LINE; i < inputReport.size(); i++) {
            String[] data = inputReport.get(i).split(SPLITERATOR);
            transactions.add(new FruitTransaction(
                    FruitTransaction.Operation.valueOfOperation(data[OPERATION_TYPE_INDEX]),
                    data[OPERATION_FRUIT_INDEX],
                    Integer.parseInt(data[OPERATION_QUANTITY_INDEX])));
        }
        return transactions;
    }
}
