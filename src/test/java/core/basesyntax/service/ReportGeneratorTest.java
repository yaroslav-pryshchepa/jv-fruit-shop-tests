package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.db.FruitStorage;
import core.basesyntax.service.impl.ReportGeneratorImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ReportGeneratorTest {
    private static String headers;
    private static ReportGenerator reportGenerator;

    @BeforeAll
    static void beforeAll() {
        headers = "fruit,quantity";
        reportGenerator = new ReportGeneratorImpl();
    }

    @AfterEach
    void tearDown() {
        FruitStorage.storage.clear();
    }

    @Test
    void generateReport_withData_Ok() {
        FruitStorage.storage.put("banana", 20);
        FruitStorage.storage.put("apple", 50);
        String expected = headers
                + System.lineSeparator() + "banana,20"
                + System.lineSeparator() + "apple,50";
        assertEquals(expected, reportGenerator.generateReport());
    }

    @Test
    void generateReport_emptyStorage_Ok() {
        assertEquals(headers, reportGenerator.generateReport());
    }

    @Test
    void generateReport_singleEntry_Ok() {
        FruitStorage.storage.put("banana", 30);
        String expected = headers + System.lineSeparator() + "banana,30";
        assertEquals(expected, reportGenerator.generateReport());
    }
}
