/*
 *  RegexSearch test class
 * @author: Shubhi Agarwal
 */
import org.junit.Assert;
import org.junit.Test;

public class RegexSearchTest {

    @Test
    public void testProcess() {
        String text = "AABAACAADAABAAABAA";
        String pat = "AABA";
        RegexSearch regex = new RegexSearch();
        int count = regex.search(pat, text);
        Assert.assertEquals(3, count);
    }
}