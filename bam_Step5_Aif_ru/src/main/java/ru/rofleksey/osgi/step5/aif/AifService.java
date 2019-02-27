package ru.rofleksey.osgi.step5.aif;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.osgi.service.component.annotations.Component;
import ru.rofleksey.osgi.step5.root.NewsSource;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component(
        service = NewsSource.class,
        immediate = true
)
public class AifService implements NewsSource {
    @Override
    public List<String> getHeadlines() throws IOException {
        Document doc = Jsoup.connect("http://www.aif.ru/rss/news.php").parser(Parser.xmlParser()).timeout(5000).get();
        Elements els = doc.select("item > title");
        return els.stream().map(Element::text).collect(Collectors.toList());
    }

    @Override
    public String getSourceName() {
        return "aif.ru";
    }
}
