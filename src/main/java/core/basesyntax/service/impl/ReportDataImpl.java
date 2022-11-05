package core.basesyntax.service.impl;

import core.basesyntax.service.impl.ReportedData;

public class ReportDataImpl implements ReportedData<StringBuilder> {
    private StringBuilder correctData;

    public ReportDataImpl(StringBuilder buildedData) {
        correctData = buildedData;
    }

    public StringBuilder getData() {
        return correctData;
    }
}
