package ru.rofleksey.osgi.step5.aif;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class AifServiceTest {
    @Test
    void getHeadlinesTest() throws IOException {
        AifService service = new AifService();
        System.out.println(service.getHeadlines());
    }

}