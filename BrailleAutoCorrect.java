import java.util.*;

public class BrailleAutoCorrect {
    static String[] dictionary = {"hello", "help", "held", "hell", "hero", "world", "word", "ward", "hard", "hold", "hole"};

    public static int editDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];

        for(int i=0; i<=m; i++) dp[i][0] = i;
        for(int j=0; j<=n; j++) dp[0][j] = j;

        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
                }
            }
        }
        return dp[m][n];
    }

    public static List<String> autocorrect(String input) {
        int minDistance = Integer.MAX_VALUE;
        List<String> suggestions = new ArrayList<>();

        for(String word : dictionary) {
            int distance = editDistance(input, word);
            if(distance < minDistance) {
                minDistance = distance;
                suggestions.clear();
                suggestions.add(word);
            } else if(distance == minDistance) {
                suggestions.add(word);
            }
        }
        return suggestions;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a word to get suggestions:");
        String input = sc.nextLine();
        List<String> result = autocorrect(input);
        System.out.println("Suggestions: " + result);
    }
}
