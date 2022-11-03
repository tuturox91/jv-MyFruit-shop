package core.basesyntax;

import core.basesyntax.db.ShopStorage;
import core.basesyntax.service.DataReader;
import core.basesyntax.service.FileReaderFromCSV;
import core.basesyntax.service.OperationProviderImpl;
import core.basesyntax.service.operations.Operation;

import java.nio.file.Path;
import java.util.List;

public class MainProgram {
    private static final String CSV_FILE_PATH = "src//main//resources//fruit_data.csv";
    private static final String DATA_SPLITTER = ",";


    public static void main(String[] args) {

        DataReader reader = new FileReaderFromCSV(Path.of(CSV_FILE_PATH), DATA_SPLITTER);

        List<FruitTransaction> transactionList = reader.readData();

        for (FruitTransaction transaction: transactionList) {
            Operation operation = new OperationProviderImpl(transaction).getOperation();

            operation.doWork(transaction);
        }

        ShopStorage.storageItems.forEach((s, integer) -> System.out.println("Name: " + s + " Count: " + integer));


    }
}
