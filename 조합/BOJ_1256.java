package 조합;

import java.io.*;
import java.util.*;

public class BOJ_1256 {
    static int N, M, K;
    static int[][] D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        D = new int[202][202];

        for (int i=0; i<201; i++){
            for (int j=0; j<i+1; j++){
                if (j == 0 || j == i){
                    D[i][j] = 1;
                }
                else {
                    D[i][j] = D[i-1][j-1] + D[i-1][j];
                    if (D[i][j] > 1000000000){
                        D[i][j] = 1000000001;
                    }
                }
            }
        }

        if (D[N+M][M] < K){
            System.out.println("-1");
        }
        else {
            while (!(N ==0 && M==0)){
                if (D[N-1+M][M] >= K){
                    System.out.print("a");
                    N -= 1;
                }
                else {
                    System.out.print("z");
                    K -= D[N-1+M][M];
                    M -= 1;
                }
            }
        }
    }
}
