/*
 * RegexSearch Class to search a pattern using regular expression
 * @author: Shubhi Agarwal
 */


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexSearch
{
    //search pattern in the text using Regular Expression
    public int search(String str, String text)
    {
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(text);
        int count = 0;
        while (m.find())
            count++;

        return count;
    }

}

