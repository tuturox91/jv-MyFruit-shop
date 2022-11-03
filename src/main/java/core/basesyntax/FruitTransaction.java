package core.basesyntax;

public class FruitTransaction {
    private OperationType operation;
    private String fruitName;
    private int quantity;

    public FruitTransaction(OperationType operation, String fruitName, int quantity) {
        this.operation = operation;
        this.fruitName = fruitName;
        this.quantity = quantity;
    }

    public OperationType getOperation() {
        return operation;
    }

    public String getFruitName() {
        return fruitName;
    }

    public int getQuantity() {
        return quantity;
    }

    public enum OperationType {
        BALANCE("b"),
        SUPPLY("s"),
        PURCHASE("p"),
        RETURN("r");

        private String operation;

        OperationType(String operation) {
            this.operation = operation;
        }

        public String getOperation() {
            return operation;
        }

        public static OperationType getOperationFromLetter(String letterString) {
            for (OperationType operation : values())
                if (operation.getOperation().equals(letterString)) {
                    return operation;
                }
            return null;
        }
    }
}
