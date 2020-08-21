/*
 * Suffix tree test class
 * @author: Shubhi Agarwal
 */
package preprocess;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SuffixTreeTest {

    @Test
    public void testSearch1() {
        String text = "Ram is a good boy";
        SuffixTree st1 = new SuffixTree(text);
        st1.buildTree(text);
        int match = st1.search("a");
        Assert.assertEquals(2, match);
    }
    @Test
    public void testSearch2() {
        String text = "Ram is a good boy";
        SuffixTree st1 = new SuffixTree(text);
        st1.buildTree(text);
        int match = st1.search(" ");
        Assert.assertEquals(4, match);
    }
    @Test
    public void testSearch3() {
        String text = "Ram is a good boy";
        SuffixTree st1 = new SuffixTree(text);
        st1.buildTree(text);
        int match = st1.search("o");
        Assert.assertEquals(3, match);
    }
    @Test
    public void testSearch4() {
        String text = "Ram is a good boy";
        SuffixTree st1 = new SuffixTree(text);
        st1.buildTree(text);
        int match = st1.search("boy");
        Assert.assertEquals(1, match);
    }
    @Test
    public void testSearch5() {
        String text = "Ram is a good boy";
        SuffixTree suffixTree1 = new SuffixTree(text);
        suffixTree1.buildTree(text);
        int match = suffixTree1.search("is");
        Assert.assertEquals(1, match);
    }
}