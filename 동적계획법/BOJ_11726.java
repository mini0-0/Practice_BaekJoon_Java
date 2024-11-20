package 동적계획법;

import java.io.*;

public class BOJ_11726 {
    static long mod = 10007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long D[] = new long[1001];
        D[1] = 1;
        D[2] = 2;
        for(int i=3; i<=N; i++){
            D[i] = (D[i-1] + D[i-2])%mod;
        }
        System.out.println(D[N]);
    }
}
