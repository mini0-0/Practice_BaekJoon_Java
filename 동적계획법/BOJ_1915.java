package 동적계획법;

import java.io.*;
import java.util.*;

public class BOJ_1915 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long D[][] = new long[1001][1001];
        long max = 0;

        for (int i = 0; i < N; i++){
            String num = br.readLine();
            for (int j = 0; j < M; j++){
                D[i][j] = Long.parseLong(String.valueOf(num.charAt(j)));
                if (D[i][j] == 1 && j > 0 && i >0){
                    D[i][j] = Math.min(D[i - 1][j - 1], Math.min(D[i - 1][j], D[i][j - 1]))  + D[i][j];
                }
                if (D[i][j] > max){
                    max = D[i][j];
                }
            }
        }

        System.out.println(max*max);
    }
}
