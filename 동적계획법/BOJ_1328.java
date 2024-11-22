package 동적계획법;

import java.io.*;
import java.util.*;

public class BOJ_1328 {
    static long mod = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long D[][][] = new long[101][101][101];
        D[1][1][1] = 1;

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());



        for (int n=2; n <= N; n++) {
            for (int l=1; l<=L; l++){
                for (int r=1; r<=R; r++){
                    D[n][l][r] = ((D[n-1][l-1][r] + D[n-1][l][r-1]) + D[n-1][l][r] * (n-2)) % mod;
                }
            }
        }
        System.out.println(D[N][L][R]);
    }
}
