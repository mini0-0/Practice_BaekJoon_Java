package 조합;

import java.io.*;

public class BOJ_1947 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long mod = 1000000000;
        long D[] = new long[1000001];
        D[1] = 0;
        D[2] = 1;
        for (int i=3; i<=N; i++){
            D[i] = (i-1) * (D[i-1]+D[i-2]) % mod;
        }
        System.out.println(D[N]);
    }
}
