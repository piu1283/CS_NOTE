package per.cc.test;

public class interview2 {
    public static void main(String[] args) {
        System.out.println(cal("accbc","*b*c*"));
    }

    private static boolean cal(String T, String P){
        boolean[][] dp = new boolean[T.length() + 1][P.length() + 1];
        dp[0][0] = true;
        for(int i = 1; i <= P.length(); i++) dp[0][i] = dp[0][i - 1] && P.charAt(i - 1) == '*';
        for(int i = 1; i <= T.length(); i++){
            for(int j = 1; j <= P.length(); j++){
                char t = T.charAt(i - 1);
                char p = P.charAt(j - 1);
                if(t == p || p == '?'){
                    dp[i][j] = dp[i - 1][j - 1];
                }else if(p == '*'){
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[T.length()][P.length()];
    }

}