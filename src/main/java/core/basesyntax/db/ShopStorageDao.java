package core.basesyntax.db;

import java.util.Map;

public interface ShopStorageDao {
    int getItemCount(String key);
    void addOrReplace(String key, int count);

    Map<String, Integer> getAllData();
}
