package 동적계획법;

import java.io.*;
import java.util.*;

public class BOJ_2342 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");


        int D[][][] = new int[100001][5][5];

        int mp[][] = {
                { 0, 2, 2, 2, 2 },
                { 2, 1, 3, 4, 3 },
                { 2, 3, 1, 3, 4 },
                { 2, 4, 3, 1, 3 },
                { 2, 3, 4, 3, 1 } };

        int n = 0, s = 1;

        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                for (int k = 0; k < 100001; k++)
                    D[k][i][j] = 100001 * 4;

        D[0][0][0] = 0;


        while (true) {
            n = Integer.parseInt(st.nextToken());
            if (n == 0)
                break;

            // 오른발 이동 계산
            for (int i = 0; i < 5; i++) {
                if (n == i)
                    continue;
                for (int j = 0; j < 5; j++) {
                    D[s][i][n] = Math.min(D[s - 1][i][j] + mp[j][n], D[s][i][n]);
                }
            }

            // 왼발 이동 계산
            for (int j = 0; j < 5; j++) {
                if (n == j)
                    continue;
                for (int i = 0; i < 5; i++) {
                    D[s][n][j] = Math.min(D[s - 1][i][j] + mp[i][n], D[s][n][j]);
                }
            }

            s++;
        }

        s--;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                min = Math.min(min, D[s][i][j]);

        System.out.println(min);
    }
}
