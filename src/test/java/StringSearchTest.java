/*
 *  StringSearchTest class
 * @author: Shubhi Agarwal
 */
import org.junit.Assert;
import org.junit.Test;

public class StringSearchTest {
    @Test
    public void testSearchPattern()
    {
        String txt = "AABAACAADAABAAABAA";
        String pat = "AABA";
        StringSearch ss = new StringSearch();
        int count = ss.search(txt, pat);
        Assert.assertEquals(3, count);
    }

}
