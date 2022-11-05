package core.basesyntax;

import core.basesyntax.db.ShopStorage;
import core.basesyntax.db.ShopStorageDao;
import core.basesyntax.db.ShopStorageDaoImpl;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.*;
import core.basesyntax.service.impl.*;
import core.basesyntax.service.operations.Operation;

import java.nio.file.Path;
import java.util.List;

public class MainProgram {
    private static final String CSV_FILE_PATH = "src//main//resources//fruit_data.csv";
    private static final String DATA_SPLITTER = ",";


    public static void main(String[] args) {

        DataReader<List<String>> CSV_reader = new ReadDataFromCSV(Path.of(CSV_FILE_PATH));

        List<String> data = CSV_reader.readData();

        FruitTransactionBuilder fruitTransactionBuilder = new FruitTransactionBuilderFromCSV(data, DATA_SPLITTER);

        List<FruitTransaction> fruitTransactions = fruitTransactionBuilder.buildTransactions();

        ShopStorageDao shopStorageDao = new ShopStorageDaoImpl();

        for (FruitTransaction transaction: fruitTransactions) {
            Operation operation = new OperationsFabricImpl(transaction).getOperation();

            operation.doWork(transaction, shopStorageDao);
        }

        ReportDataServiceImpl reportDataBuilder  = new ReportDataServiceImpl(shopStorageDao);

        new WriteDataToCSV(Path.of("report.csv")).writeData(reportDataBuilder.buildData());

        ShopStorage.storageItems.forEach((s, integer) -> System.out.println("Name: " + s + " Count: " + integer));
    }
}
