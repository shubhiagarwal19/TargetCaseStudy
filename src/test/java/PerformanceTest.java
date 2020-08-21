/*
 * Test class for performance testing
 * @author: Shubhi Agarwal
 */

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import preprocess.SuffixTree;

public class PerformanceTest {
    String file1, file2, file3;

    @Before
    public void setUp() throws Exception {
        UserInterface ui = new UserInterface();
        file1 = ui.readFileAsString("src/main/resources/File1.txt");
        file2 = ui.readFileAsString("src/main/resources/File2.txt");
        file3 = ui.readFileAsString("src/main/resources/File3.txt");
    }

    @After
    public void tearDown() throws Exception{
    }

    @Test
    public void generateRandomStringTest() throws Exception {
        long start = System.currentTimeMillis();
        String generatedString = RandomStringUtils.randomAlphabetic(3);
        long end = System.currentTimeMillis();
        System.out.println("generatedString takes " +
                (end - start) + "ms");
    }

    // performance test on search methods by running 2M searches with random search string
     @Test
    public void test2MSearch() throws Exception {
        StringSearch ss = new StringSearch();
        RegexSearch regex = new RegexSearch();
        SuffixTree st1 = new SuffixTree(file1);
        SuffixTree st2 = new SuffixTree(file2);
        SuffixTree st3 = new SuffixTree(file3);
        st1.buildTree(file1);
        st2.buildTree(file2);
        st3.buildTree(file3);

        // simple string search performance
        long start = System.currentTimeMillis();
        for (int i = 0; i< 2000000; i++) {
            String generatedString = RandomStringUtils.randomAlphabetic(2);
            ss.search(file1 , generatedString);
            ss.search(file2 , generatedString);
            ss.search(file3 , generatedString);
        }
        long end = System.currentTimeMillis();
        System.out.println("StringSearch takes " +
                (end - start) + "ms");

        // regular expression performance
        long start1 = System.currentTimeMillis();
        for (int i = 0; i< 2000000; i++) {
            String generatedString = RandomStringUtils.randomAlphabetic(2);
            regex.search(generatedString,file1 );
            regex.search(generatedString,file2 );
            regex.search(generatedString,file3 );
        }
        long end1 = System.currentTimeMillis();
        System.out.println("RegexSearch takes " +
                (end1 - start1) + "ms");

        // suffix tree performance
        long start2 = System.currentTimeMillis();
        for (int i = 0; i< 2000000; i++) {
            String generatedString = RandomStringUtils.randomAlphabetic(2);
            st1.search(generatedString);
            st2.search(generatedString);
            st3.search(generatedString);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("SuffixSearch takes " +
                (end2 - start2) + "ms");

    }
}
