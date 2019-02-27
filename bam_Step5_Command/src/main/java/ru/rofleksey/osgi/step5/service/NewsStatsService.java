package ru.rofleksey.osgi.step5.service;

import org.apache.felix.service.command.CommandProcessor;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import ru.rofleksey.osgi.step5.root.NewsSource;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component(
        service = NewsStatsService.class,
        immediate = true,
        property = {
                CommandProcessor.COMMAND_SCOPE + ":String=news",
                CommandProcessor.COMMAND_FUNCTION + ":String=stats",
        }
)
public class NewsStatsService {
    public static int MAX_SIZE = 10;

    Map<String, NewsSource> sources = new HashMap<>();

    @Reference(
            service = NewsSource.class,
            cardinality = ReferenceCardinality.MULTIPLE,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unbindService"
    )
    protected void bindService(NewsSource service) {
        sources.put(service.getSourceName(), service);
    }

    protected void unbindService(NewsSource service) {
        sources.remove(service.getSourceName());
    }

    public void stats() {
        if (sources.isEmpty()) {
            System.err.println("No available sources");
        } else {
            System.out.println("Usage: news:stats [source_name]");
            StringJoiner joiner = new StringJoiner(", ");
            sources.keySet().forEach(joiner::add);
            System.out.println("Available sources: " + joiner.toString());
        }
    }

    public void stats(String source) {
        NewsSource ns = sources.get(source);
        if (ns == null) {
            System.err.println("Source '" + source + "' is not available");
            return;
        }
        try {
            getTopWords(ns.getHeadlines());
        } catch (IOException e) {
            System.err.println("Failed to get stats: " + e.toString());
        }
    }

    public void getTopWords(List<String> headlines) {
        Map<String, Long> wordMap = headlines.stream()
                .flatMap(line -> Arrays.stream(line
                        .replaceAll("[^а-яА-Яa-zA-Z0-9\\-\\s]", "")
                        .trim()
                        .split("\\s+"))
                        .map(String::toLowerCase))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<String> sortedList = wordMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(MAX_SIZE)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        StringJoiner joiner = new StringJoiner(", ");
        sortedList.forEach(joiner::add);
        System.out.println("Keywords: " + joiner.toString());
    }
}
