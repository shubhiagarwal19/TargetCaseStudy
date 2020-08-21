/*
 * Node test class
 * @author: Shubhi Agarwal
 */
package preprocess;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NodeTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetText() {
        Node n = new Node("hello", 1);
        String s = n.getText();
        Assert.assertEquals("hello", s);
    }

    @Test
    public void testSetText() {
        Node n = new Node("hello", 1);
        String s = n.getText();
        Assert.assertEquals("hello", s);
        n.setText("how");
        String s1 = n.getText();
        Assert.assertEquals("how", s1);
    }

    @Test
    public void testGetChildren() {
        Node n = new Node("hello", 1);
        List<Node> s = n.getChildren();
        Assert.assertEquals(0, s.size());
    }

    @Test
    public void testSetChildren() {
        Node n = new Node("h", 1);
        Node n1 = new Node("ello", 1);
        List<Node> l = new ArrayList<>();
        l.add(n1);
        n.setChildren(l);
        Assert.assertEquals(1, n.getChildren().size());
    }

    @Test
    public void testGetIndex() {
        Node n = new Node("h", 1);
        int i = n.getIndex();
        Assert.assertEquals(1, i);
    }

    @Test
    public void testSetIndex() {
        Node n = new Node("h", 1);
        int i = n.getIndex();
        Assert.assertEquals(1, i);
        n.setIndex(2);
        i = n.getIndex();
        Assert.assertEquals(2, i);
    }
}