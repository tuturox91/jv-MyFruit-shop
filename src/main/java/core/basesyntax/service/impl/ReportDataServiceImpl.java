package core.basesyntax.service.impl;

import core.basesyntax.db.ShopStorageDao;
import core.basesyntax.service.ReportDataService;

import java.util.Map;

public class ReportDataServiceImpl implements ReportDataService<ReportDataServiceImpl.ReportedData,ShopStorageDao> {

    private String reportHeaderLine = "fruit,quantity";
    private String reportWordSeparator = ",";
    private ShopStorageDao dao;

    public ReportDataServiceImpl(ShopStorageDao dao, String reportHeaderLine, String reportWordSeparator) {
        this.reportHeaderLine = reportHeaderLine;
        this.reportWordSeparator = reportWordSeparator;
        this.dao = dao;
    }

    public ReportDataServiceImpl(ShopStorageDao dao) {
        this.dao = dao;
    }

    @Override
    public ReportedData buildData() {
        Map<String, Integer> data = dao.getAllData();
        StringBuilder reportDataBuilder = new StringBuilder(reportHeaderLine);
        data.forEach((fruitName, count) -> {
            reportDataBuilder.append(System.lineSeparator())
                    .append(fruitName)
                    .append(reportWordSeparator)
                    .append(count);
          });
          return new ReportedData(reportDataBuilder);
    }

    protected class ReportedData {
        StringBuilder stringBuilder;

        private ReportedData(StringBuilder builder) {
            stringBuilder = builder;
        }

        StringBuilder getData() {
            return stringBuilder;
        }
    }
}
