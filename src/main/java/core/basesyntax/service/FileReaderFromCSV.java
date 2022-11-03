package core.basesyntax.service;

import core.basesyntax.FruitTransaction;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileReaderFromCSV implements DataReader {
    private static final int LINE_SKIP_IN_DATA_FILE = 1;
    private static final int POSITION_OF_OPERATION_LETTER = 0;
    private static final int POSITION_OF_FRUIT_NAME = 1;
    private static final int POSITION_OF_QUANTITY = 2;



    private Path filePath;
    private String dataSplitter;

    public FileReaderFromCSV(Path filePath, String dataSplitterRegex) {
        this.filePath = filePath;
        this.dataSplitter = dataSplitterRegex;
    }

    @Override
    public List<FruitTransaction> readData() {
        List<String[]> fruitData = new ArrayList<>();
        try {
            fruitData = Files.lines(filePath, StandardCharsets.UTF_8)
                    .skip(LINE_SKIP_IN_DATA_FILE)
                    .map(w -> w.split(dataSplitter))
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if(fruitData.size() == 0) {
            throw new RuntimeException("File: " + filePath + " is empty");
        } else {
            return makeFruitTransactionFromData(fruitData);
        }
    }

    private List<FruitTransaction> makeFruitTransactionFromData(List<String[]> fruitData) {
        List<FruitTransaction> transactions = new ArrayList<>();

        fruitData.forEach(strings -> {
            FruitTransaction.OperationType operationType =
                    FruitTransaction.OperationType.getOperationFromLetter(strings[POSITION_OF_OPERATION_LETTER]);
            if(operationType == null) {
                throw new RuntimeException("Operations: " + "'" + strings[POSITION_OF_OPERATION_LETTER] + "'" + " does not exist.");
            }
            FruitTransaction transaction =
                    new FruitTransaction(operationType,
                            strings[POSITION_OF_FRUIT_NAME],
                            Integer.parseInt(strings[POSITION_OF_QUANTITY]));
            transactions.add(transaction);
        });
        if(transactions.size() == 0) {
            throw new RuntimeException("Data in csv table is wrong!");
        } else {
            return  transactions;
        }
    }
}
