package core.basesyntax.service.impl;

import core.basesyntax.db.ShopStorageDao;
import core.basesyntax.service.ReportDataBuilder;

import java.util.Map;

public class ReportDataBuilderImpl implements ReportDataBuilder<ReportDataImpl, ShopStorageDao> {

    private String reportHeaderLine = "fruit,quantity";
    private String reportWordSeparator = ",";
    private ShopStorageDao dao;



    public ReportDataBuilderImpl(ShopStorageDao dao,String reportHeaderLine, String reportWordSeparator) {
        this.reportHeaderLine = reportHeaderLine;
        this.reportWordSeparator = reportWordSeparator;
        this.dao = dao;
    }

    public ReportDataBuilderImpl(ShopStorageDao dao) {
        this.dao = dao;
    }

    @Override
    public ReportDataImpl buildData() {
        Map<String, Integer> data = dao.getAllData();
        StringBuilder reportDataBuilder = new StringBuilder(reportHeaderLine);
        data.forEach((fruitName, count) -> {
            reportDataBuilder.append(System.lineSeparator())
                    .append(fruitName)
                    .append(reportWordSeparator)
                    .append(count);
          });
          return new ReportDataImpl(reportDataBuilder);
    }
}
