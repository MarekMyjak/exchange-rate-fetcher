package pl.parser.nbp;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class XmlFileNamesFetcherTest {

    @Test
    public void testSingleFile() {
        List<String> dirFileNames = Collections.singletonList("dir2013.txt");
        List<String> xmlFileNames = XmlFileNamesFetcher.INSTANCE.fetch(dirFileNames, DateCreator.INSTANCE.create("2013-01-28"), DateCreator.INSTANCE.create("2013-01-31"));
        assertEquals(4, xmlFileNames.size());
        assertTrue(xmlFileNames.contains("c019z130128"));
        assertTrue(xmlFileNames.contains("c020z130129"));
        assertTrue(xmlFileNames.contains("c021z130130"));
        assertTrue(xmlFileNames.contains("c022z130131"));
    }

    @Test
    public void testMultipleFiles() {
        List<String> dirFileNames = Arrays.asList("dir2013.txt", "dir2012.txt");
        List<String> xmlFileNames = XmlFileNamesFetcher.INSTANCE.fetch(dirFileNames, DateCreator.INSTANCE.create("2012-12-28"), DateCreator.INSTANCE.create("2013-01-03"));
        assertEquals(4, xmlFileNames.size());
        assertTrue(xmlFileNames.contains("c001z130102"));
        assertTrue(xmlFileNames.contains("c002z130103"));
        assertTrue(xmlFileNames.contains("c251z121228"));
        assertTrue(xmlFileNames.contains("c252z121231"));
    }

    @Test
    public void testMultipleYears() {
        List<String> dirFileNames = Arrays.asList("dir2016.txt", "dir2017.txt", "dir.txt");
        List<String> xmlFileNames = XmlFileNamesFetcher.INSTANCE.fetch(dirFileNames, DateCreator.INSTANCE.create("2016-12-11"), DateCreator.INSTANCE.create("2018-02-22"));
        assertEquals(303, xmlFileNames.size());
    }
}
