import java.util.*;

class Solution{
    int[]dp;

    static public int fib(n){
       dp=new int[n+1];

       for(int i=0li<n;i++){
        dp[i]=-1;
       }
       
    }
    return solve(n);
}

private int solve(n){
    if(n<=1){
        return n;
    }

    if(dp[n]!=-1){
      return dp[n];
    }

    dp[n]=solve(n-1)+solve(n-2);
    return dp[n];

}