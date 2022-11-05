package core.basesyntax.service;

import core.basesyntax.db.ShopStorageDao;

public interface ReportDataBuilder<T, V extends ShopStorageDao> {
    T buildData();
}
