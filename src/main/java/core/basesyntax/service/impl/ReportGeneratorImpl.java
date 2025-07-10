package core.basesyntax.service.impl;

import core.basesyntax.dao.FruitDao;
import core.basesyntax.service.ReportGenerator;
import java.util.Map;

public class ReportGeneratorImpl implements ReportGenerator {
    private final FruitDao fruitDao;

    public ReportGeneratorImpl(FruitDao fruitDao) {
        this.fruitDao = fruitDao;
    }

    @Override
    public String getReport() {
        Map<String, Integer> allFruits = fruitDao.getAll();
        StringBuilder report = new StringBuilder();
        report.append("fruit,quantity\n");
        for (Map.Entry<String, Integer> entry : allFruits.entrySet()) {
            report.append(entry.getKey() + "," + entry.getValue() + "\n");
        }
        return report.toString();
    }
}
