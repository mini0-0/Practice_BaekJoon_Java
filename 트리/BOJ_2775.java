package 트리;

import java.io.*;
import java.util.*;

public class BOJ_2775 {
    static int T, N, K;
    static int[][] D;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        D = new int[15][15];
        for (int i = 0; i < 15; i++) {
            D[i][1] = 1;
            D[0][i] = i;
        }
        for (int i = 1; i < 15; i++) {
            for (int j = 2; j < 15; j++) {
                D[i][j] = D[i][j-1] + D[i-1][j];
            }
        }
        Scanner sc = new Scanner(System.in);
        T = Integer.parseInt(sc.nextLine());

        for(int i=0; i<T; i++) {
            K = Integer.parseInt(sc.nextLine());
            N = Integer.parseInt(sc.nextLine());
            System.out.println(D[K][N]);
        }
    }
}
