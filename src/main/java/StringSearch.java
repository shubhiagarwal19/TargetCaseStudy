/*
 * StringSearch Class to search a pattern using simple string traversal
 * @author: Shubhi Agarwal
 */
public class StringSearch {
    //search pattern using naive method
    public int search(String text, String pat) {
        int M = pat.length();
        int N = text.length();
        int count = 0;
        for (int i = 0; i <= N - M; i++)
        {
            int j;
            for (j = 0; j < M; j++)
                if (text.charAt(i + j) != pat.charAt(j))
                    break;
            if (j == M) {
                count++;

            }
        }
        return count;
    }

}


