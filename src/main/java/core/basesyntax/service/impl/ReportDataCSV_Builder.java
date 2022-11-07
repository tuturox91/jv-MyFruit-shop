package core.basesyntax.service.impl;

import core.basesyntax.db.ShopStorageDao;
import core.basesyntax.service.ReportDataService;

import java.util.Map;

public class ReportDataCSV_Builder implements ReportDataService<String, ShopStorageDao> {

    private String reportHeaderLine = "fruit,quantity";
    private String reportWordSeparator = ",";
    private ShopStorageDao dao;



    public ReportDataCSV_Builder(ShopStorageDao dao, String reportHeaderLine, String reportWordSeparator) {
        this.reportHeaderLine = reportHeaderLine;
        this.reportWordSeparator = reportWordSeparator;
        this.dao = dao;
    }

    public ReportDataCSV_Builder(ShopStorageDao dao) {
        this.dao = dao;
    }

    @Override
    public String buildData() {
        Map<String, Integer> data = dao.getAllData();
        StringBuilder reportDataBuilder = new StringBuilder(reportHeaderLine);
        data.forEach((fruitName, count) -> {
            reportDataBuilder.append(System.lineSeparator())
                    .append(fruitName)
                    .append(reportWordSeparator)
                    .append(count);
          });
          return reportDataBuilder.toString();
    }
}
