package ru.rofleksey.osgi.step5.root;

import java.io.IOException;
import java.util.List;

public interface NewsSource {
    List<String> getHeadlines() throws IOException;

    String getSourceName();
}
