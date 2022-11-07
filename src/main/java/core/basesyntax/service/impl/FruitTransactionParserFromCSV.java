package core.basesyntax.service.impl;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.FruitTransactionParser;

import java.util.ArrayList;
import java.util.List;

public class FruitTransactionParserFromCSV implements FruitTransactionParser {

    private static final int POSITION_OF_OPERATION_LETTER = 0;
    private static final int POSITION_OF_FRUIT_NAME = 1;
    private static final int POSITION_OF_QUANTITY = 2;

    private String dataSplitter;
    private List<String> fruitData;

    public FruitTransactionParserFromCSV(List<String> fruitData, String dataSplitter) {
        this.fruitData = fruitData;
        this.dataSplitter = dataSplitter;
    }

    @Override
    public List<FruitTransaction> parseData() {
        List<FruitTransaction> transactions = new ArrayList<>();
        fruitData.forEach(lines -> {
            String[] operationString = lines.split(dataSplitter);
            FruitTransaction.OperationType operationType =
                    FruitTransaction.OperationType.getOperationFromLetter(operationString[POSITION_OF_OPERATION_LETTER]);
            String fruitName = operationString[POSITION_OF_FRUIT_NAME];
            int operationQuantity = Integer.parseInt(operationString[POSITION_OF_QUANTITY]);

            transactions.add(new FruitTransaction(operationType, fruitName, operationQuantity));
        });
        return transactions;
    }
}

