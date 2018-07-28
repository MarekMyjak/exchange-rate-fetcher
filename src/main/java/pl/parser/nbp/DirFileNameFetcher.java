package pl.parser.nbp;

import com.google.common.collect.ImmutableList;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum DirFileNameFetcher {
    INSTANCE;

    private static final String DIR_FILE_PATTERN = "dir%d.txt";

    public List<String> fetch(LocalDate startData, LocalDate endDate) {
        ImmutableList.Builder<String> listBuilder = new ImmutableList.Builder<>();
        int endYear = endDate.getYear() + 1;
        if (LocalDate.now().getYear() == endDate.getYear()) {
            listBuilder.add("dir.txt");
            endYear -= 1;
        }
        listBuilder.addAll(IntStream.range(startData.getYear(), endYear)
                .mapToObj(year -> String.format(DIR_FILE_PATTERN, year))
                .collect(Collectors.toList()));
        return listBuilder.build();
    }
}

