package core.basesyntax.service;

import core.basesyntax.db.ShopStorageDao;

public interface ReportDataService<T, V extends ShopStorageDao> {
    T buildData();
}
