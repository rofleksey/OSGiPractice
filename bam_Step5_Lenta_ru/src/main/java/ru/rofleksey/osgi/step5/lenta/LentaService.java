package ru.rofleksey.osgi.step5.lenta;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.osgi.service.component.annotations.Component;
import ru.rofleksey.osgi.step5.root.NewsSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component(
        service = NewsSource.class,
        immediate = true
)
public class LentaService implements NewsSource {
    @Override
    public List<String> getHeadlines() throws IOException {
        ArrayList<String> result = new ArrayList<>();
        String answer = Jsoup.connect("https://api.lenta.ru/lists/latest").timeout(5000).ignoreContentType(true).execute().body();
        JSONObject json = new JSONObject(answer);
        JSONArray arr = json.getJSONArray("headlines");
        for (int i = 0; i < arr.length(); i++) {
            JSONObject cur = arr.getJSONObject(i);
            JSONObject info = cur.getJSONObject("info");
            result.add(info.getString("title"));
        }
        return result;
    }

    @Override
    public String getSourceName() {
        return "lenta.ru";
    }
}
