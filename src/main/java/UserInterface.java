/*
 *Class to Prompt the user to enter a search term and search method,
 * execute the search, and return results
 * @author: Shubhi Agarwal
 */
import preprocess.SuffixTree;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class UserInterface {

    //search user provided pattern with selected mode of serach in the text files
    public ArrayList<Integer> searchPatternInFiles(String pattern, int method) throws Exception {

        ArrayList<Integer> matches = new ArrayList<Integer>(3);
        //Convert text file data to string
        String file1 = readFileAsString("src/main/resources/File1.txt");
        String file2 = readFileAsString("src/main/resources/File2.txt");
        String file3 = readFileAsString("src/main/resources/File3.txt");

        int count1 = 0, count2 = 0, count3 = 0;
        switch (method) {
            //search pattern using simple string matching
            case 1:
                long start1 = System.currentTimeMillis();
                StringSearch ss = new StringSearch();
                count1 = ss.search(file1, pattern);
                count2 = ss.search(file2, pattern);
                count3 = ss.search(file3, pattern);
                long end1 = System.currentTimeMillis();
                System.out.println("Elapsed time: " +
                        (end1 - start1) + "ms");
                break;
            //search pattern using regular expressions
            case 2:
                long start2 = System.currentTimeMillis();
                RegexSearch regex = new RegexSearch();
                count1 = regex.search(pattern, file1);
                count2 = regex.search(pattern, file2);
                count3 = regex.search(pattern, file3);
                long end2 = System.currentTimeMillis();
                System.out.println("Elapsed time: " +
                        (end2 - start2) + "ms");
                break;
            //search pattern by preprocessing the file data and then searching the index
            //by using Suffix tree
            case 3:
                SuffixTree suffixTree1 = new SuffixTree(file1);
                SuffixTree suffixTree2 = new SuffixTree(file2);
                SuffixTree suffixTree3 = new SuffixTree(file3);
                suffixTree1.buildTree(file1);
                suffixTree2.buildTree(file2);
                suffixTree3.buildTree(file3);
                long start3 = System.currentTimeMillis();
                count1 = suffixTree1.search(pattern);
                count2 = suffixTree2.search(pattern);
                count3 = suffixTree3.search(pattern);
                long end3 = System.currentTimeMillis();
                System.out.println("Elapsed time: " +
                        (end3 - start3) + "ms");
                break;

            default:
                System.out.println("No search method selected");
                break;
        }


        matches.add(count1);
        matches.add(count2);
        matches.add(count3);
        return matches;
    }

    //read the file and convert file data to String
    public String readFileAsString(String fileName)throws Exception
    {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    // Prompt the user to enter a search term and search method, execute the search, and return results
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Search String: ");
        String str = sc.nextLine();
        System.out.print("Select Search Method:\n 1) String Match\n 2) Regular Expression" +
                "\n 3) Indexed\nEnter:  ");
        int method = sc.nextInt();
        System.out.println("Search Results:\n");
        UserInterface ui = new UserInterface();
        List matches = ui.searchPatternInFiles(str, method);

        System.out.println("File1.txt: "+matches.get(0)+" matches");
        System.out.println("File2.txt: "+matches.get(1)+" matches");
        System.out.println("File3.txt: "+matches.get(2)+" matches");
    }

}
