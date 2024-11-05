package 트리;

import java.util.*;
import java.io.*;

public class BOJ_11725_ver1 {
    static List<Integer>[] tree;
    static boolean[] visited;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1];
        visited = new boolean[N+1];
        answer = new int[N+1];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            tree[n1].add(n2);
            tree[n2].add(n1);
        }

        DFS(1);

        for(int i=2; i < N+1; i++){
            System.out.println(answer[i]);
        }

    }

    static void DFS(int num) {
        visited[num] = true;
        for (int i : tree[num]) {
            if (!visited[i]) {
                answer[i] = num;
                DFS(i);
            }

        }
    }

}
