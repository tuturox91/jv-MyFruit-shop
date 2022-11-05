package core.basesyntax.service;

public interface DataWriter<T extends ReportDataService> {
    void writeData(T dataBuilder);
}
