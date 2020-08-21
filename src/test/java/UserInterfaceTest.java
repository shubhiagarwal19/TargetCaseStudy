/*
 * User Interface Test class to compare the search results with different search methods
 * @author: Shubhi Agarwal
 */
import org.junit.*;
import java.util.List;

public class UserInterfaceTest {

    //test search by simple string search method
    @Test
    public void testSearchPatternInFilesUsingMethod1() throws Exception {
        UserInterface ui = new UserInterface();
        List matches = ui.searchPatternInFiles("junk", 1);
        Assert.assertEquals(7,matches.get(0));
        Assert.assertEquals(8,matches.get(1));
        Assert.assertEquals(5,matches.get(2));

        List matches1 = ui.searchPatternInFiles("you", 1);
        Assert.assertEquals(2,matches1.get(0));
        Assert.assertEquals(1,matches1.get(1));
        Assert.assertEquals(8,matches1.get(2));

        List matches2 = ui.searchPatternInFiles(" ", 1);
        Assert.assertEquals(158,matches2.get(0));
        Assert.assertEquals(241,matches2.get(1));
        Assert.assertEquals(187,matches2.get(2));
    }

    //test search by regex method
    @Test
    public void testSearchPatternInFilesUsingMethod2() throws Exception {
        UserInterface ui = new UserInterface();
        List matches = ui.searchPatternInFiles("junk", 2);
        Assert.assertEquals(7,matches.get(0));
        Assert.assertEquals(8,matches.get(1));
        Assert.assertEquals(5,matches.get(2));

        List matches1 = ui.searchPatternInFiles("you", 2);
        Assert.assertEquals(2,matches1.get(0));
        Assert.assertEquals(1,matches1.get(1));
        Assert.assertEquals(8,matches1.get(2));

        List matches2 = ui.searchPatternInFiles(" ", 2);
        Assert.assertEquals(158,matches2.get(0));
        Assert.assertEquals(241,matches2.get(1));
        Assert.assertEquals(187,matches2.get(2));
    }

    //test search by suffix tree search method
    @Test
    public void testSearchPatternInFilesUsingMethod3() throws Exception {
        UserInterface ui = new UserInterface();
        List matches = ui.searchPatternInFiles("junk", 3);
        Assert.assertEquals(7,matches.get(0));
        Assert.assertEquals(8,matches.get(1));
        Assert.assertEquals(5,matches.get(2));

        List matches1 = ui.searchPatternInFiles("you", 3);
        Assert.assertEquals(2,matches1.get(0));
        Assert.assertEquals(1,matches1.get(1));
        Assert.assertEquals(8,matches1.get(2));

        List matches2 = ui.searchPatternInFiles(" ", 3);
        Assert.assertEquals(158,matches2.get(0));
        Assert.assertEquals(241,matches2.get(1));
        Assert.assertEquals(187,matches2.get(2));
    }

    //test file data conversion to string
    @Test
    public void readFileAsString() throws Exception {
        UserInterface ui = new UserInterface();
        String data = ui.readFileAsString(
                "src/main/resources/File1.txt");
        boolean b = data.contains("junk");
        Assert.assertEquals(true,b);
    }

}
