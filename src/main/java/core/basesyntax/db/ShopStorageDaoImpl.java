package core.basesyntax.db;

import java.util.Map;

public class ShopStorageDaoImpl implements ShopStorageDao{

    @Override
    public int getItemCount(String key) {
        return ShopStorage.storageItems.get(key);
    }

    @Override
    public void addOrReplace(String key, int count) {
        ShopStorage.storageItems.put(key, count);
    }

    @Override
    public Map<String, Integer> getAllData() {
        return ShopStorage.storageItems;
    }
}
