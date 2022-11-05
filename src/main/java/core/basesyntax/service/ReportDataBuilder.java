package core.basesyntax.service;

import core.basesyntax.db.ShopStorageDao;
import core.basesyntax.service.impl.ReportedData;

public interface ReportDataBuilder<T, V extends ShopStorageDao> {
    T buildData();
}
