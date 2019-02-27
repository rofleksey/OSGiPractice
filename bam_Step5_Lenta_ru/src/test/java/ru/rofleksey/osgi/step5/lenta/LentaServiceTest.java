package ru.rofleksey.osgi.step5.lenta;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class LentaServiceTest {
    @Test
    void getHeadlinesTest() throws IOException {
        LentaService service = new LentaService();
        System.out.println(service.getHeadlines());
    }
}