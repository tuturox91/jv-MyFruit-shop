package core.basesyntax.dao;

import java.util.HashMap;
import java.util.Map;

public class FruitDaoImpl implements FruitDao {
    private Map<String, Integer> fruitStorage = new HashMap<>();

    @Override
    public void update(String fruitName, int fruitQuantity) {
        if (fruitName == null || fruitName.isEmpty()) {
            throw new IllegalArgumentException("Fruit name cannot be null or empty");
        }

        if (fruitQuantity < 0) {
            throw new IllegalArgumentException("Fruit quantity cannot be negative");
        }

        fruitStorage.put(fruitName, fruitQuantity);
    }

    @Override
    public Integer get(String fruitName) {
        return fruitStorage.getOrDefault(fruitName, 0);
    }

    @Override
    public Map<String, Integer> getAll() {
        return new HashMap<>(fruitStorage);
    }
}
