package core.basesyntax;

import core.basesyntax.dao.FruitDao;
import core.basesyntax.dao.FruitDaoImpl;
import core.basesyntax.io.FileReader;
import core.basesyntax.io.FileWriter;
import core.basesyntax.io.impl.FileReaderImpl;
import core.basesyntax.io.impl.FileWriterImpl;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.DataConverter;
import core.basesyntax.service.ReportGenerator;
import core.basesyntax.service.ShopService;
import core.basesyntax.service.impl.DataConverterImpl;
import core.basesyntax.service.impl.ReportGeneratorImpl;
import core.basesyntax.service.impl.ShopServiceImpl;
import core.basesyntax.strategy.OperationHandler;
import core.basesyntax.strategy.OperationStrategy;
import core.basesyntax.strategy.OperationStrategyImpl;
import core.basesyntax.strategy.impl.BalanceOperation;
import core.basesyntax.strategy.impl.PurchaseOperation;
import core.basesyntax.strategy.impl.ReturnOperation;
import core.basesyntax.strategy.impl.SupplyOperation;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        FruitDao fruitDao = new FruitDaoImpl();

        FileReader fileReader = new FileReaderImpl();
        Path readPath = Path.of("src/main/resources/reportToRead.csv");
        List<String> inputReport = fileReader.read(readPath);

        DataConverter dataConverter = new DataConverterImpl();
        List<FruitTransaction> transactions = dataConverter.convertToTransaction(inputReport);

        Map<FruitTransaction.Operation, OperationHandler> operationHandlers = new HashMap<>();
        operationHandlers.put(FruitTransaction.Operation.BALANCE, new BalanceOperation(fruitDao));
        operationHandlers.put(FruitTransaction.Operation.SUPPLY, new SupplyOperation(fruitDao));
        operationHandlers.put(FruitTransaction.Operation.PURCHASE, new PurchaseOperation(fruitDao));
        operationHandlers.put(FruitTransaction.Operation.RETURN, new ReturnOperation(fruitDao));

        OperationStrategy operationStrategy = new OperationStrategyImpl(operationHandlers);

        ShopService shopService = new ShopServiceImpl(operationStrategy);
        shopService.process(transactions);

        ReportGenerator reportGenerator = new ReportGeneratorImpl(fruitDao);
        String resultingReport = reportGenerator.getReport();

        FileWriter fileWriter = new FileWriterImpl();
        Path writePath = Path.of("src/main/resources/reportToWrite.csv");
        fileWriter.write(resultingReport, writePath);
    }
}
