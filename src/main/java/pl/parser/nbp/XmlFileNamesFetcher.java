package pl.parser.nbp;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum XmlFileNamesFetcher {
    INSTANCE;

    private static final String URL_PATTERN = "http://www.nbp.pl/kursy/xml/%s";
    private static final String UTF8_BOM = "\uFEFF";

    public List<String> fetch(List<String> dirFileNames, LocalDate startDate, LocalDate endDate) {
        return dirFileNames.stream().flatMap(dirFileName -> {
            try {
                HttpResponse<String> response = Unirest.get(String.format(URL_PATTERN, dirFileName))
                        .asString();
                String body = removeUTF8BOM(response.getBody());
                return Arrays.stream(body.split("\r\n"));
            } catch (UnirestException e) {
                throw new IllegalStateException("Something goes wrong");
            }
        }).filter(XmlFileNameChecker.INSTANCE::check)
                .filter(fileName -> {
                    LocalDate dateFromFileName = getDateFromFileName(fileName);
                    return dateFromFileName.isAfter(startDate) &&
                            dateFromFileName.isBefore(endDate) ||
                            dateFromFileName.isEqual(startDate) ||
                            dateFromFileName.isEqual(endDate);
                }).collect(Collectors.toList());
    }

    private LocalDate getDateFromFileName(String fileName) {
        int length = fileName.length();
        String year = fileName.substring(length - 6, length - 4);
        String month = fileName.substring(length - 4, length - 2);
        String day = fileName.substring(length - 2, length);
        return DateCreator.INSTANCE.create(String.format("20%s-%s-%s", year, month, day));
    }

    private static String removeUTF8BOM(String s) {
        if (s.startsWith(UTF8_BOM)) {
            s = s.substring(1);
        }
        return s;
    }
}
