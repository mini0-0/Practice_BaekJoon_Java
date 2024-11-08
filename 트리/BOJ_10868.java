package 트리;

import java.io.*;
import java.util.*;

public class BOJ_10868 {
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int treeHeight = 0;
        int lenght = N;

        while (lenght != 0) {
            lenght /= 2;
            treeHeight++;
        }

        int treeSize = (int) Math.pow(2, treeHeight + 1);
        int leftNodeStartIndex = treeSize / 2 - 1;

        tree = new long[treeSize + 1];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = Integer.MAX_VALUE;
        }

        for (int i = leftNodeStartIndex + 1; i <= leftNodeStartIndex + N; i++) {
            tree[i] = Long.parseLong(br.readLine());
        }

        setTree(treeSize - 1);

        for (int i = 0; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
             s = s + leftNodeStartIndex;
             e = e + leftNodeStartIndex;
            System.out.println(getMin(s, e));

        }

        br.close();
    }

    private static long getMin(int s, int e) {
        long Min = Integer.MAX_VALUE;
        while (s <= e) {
            if (s % 2 == 1) {
                Min = Math.min(Min, tree[s]);
                s++;
            }
            s = s / 2;
            if (e % 2 == 0) {
                Min = Math.min(Min, tree[e]);
                e--;
            }

            e = e / 2;
        }
        return Min;
    }

    private static void setTree(int i) {
        while (i != 1) {
            if (tree[i / 2] > tree[i]) {
                tree[i / 2] = tree[i];
            }
            i--;
        }

    }

}
