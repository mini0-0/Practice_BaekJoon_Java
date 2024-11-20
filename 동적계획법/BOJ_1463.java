package 동적계획법;

import java.io.*;
import java.util.*;

public class BOJ_1463 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] D = new int[N+1];
        D[1] = 0;

        for (int i=2; i <= N; i++) {
            D[i] = D[i-1]+1;
            if (i%2 == 0) {
                D[i] = Math.min(D[i], D[i/2]+1);
            }
            if (i%3 == 0) {
                D[i] = Math.min(D[i], D[i/3]+1);
            }
        }
        System.out.println(D[N]);
    }
}
