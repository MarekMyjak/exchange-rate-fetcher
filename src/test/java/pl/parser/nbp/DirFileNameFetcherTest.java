package pl.parser.nbp;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DirFileNameFetcherTest {

    @Test
    public void testWithActualYear(){
        LocalDate startDate = DateCreator.INSTANCE.create("2015-02-02");
        LocalDate endDate = LocalDate.now();

        List<String> dirFileNames = DirFileNameFetcher.INSTANCE.fetch(startDate, endDate);
        assertEquals(4, dirFileNames.size());
        assertTrue(dirFileNames.contains("dir2015.txt"));
        assertTrue(dirFileNames.contains("dir2016.txt"));
        assertTrue(dirFileNames.contains("dir2017.txt"));
        assertTrue(dirFileNames.contains("dir.txt"));
    }

    @Test
    public void testDateWithPastYear(){
        LocalDate startDate = DateCreator.INSTANCE.create("2013-02-02");
        LocalDate endDate = DateCreator.INSTANCE.create("2015-02-02");

        List<String> dirFileNames = DirFileNameFetcher.INSTANCE.fetch(startDate, endDate);
        assertEquals(3, dirFileNames.size());
        assertTrue(dirFileNames.contains("dir2013.txt"));
        assertTrue(dirFileNames.contains("dir2014.txt"));
        assertTrue(dirFileNames.contains("dir2015.txt"));
    }
}
