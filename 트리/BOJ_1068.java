package 트리;

import java.io.*;
import java.util.*;

public class BOJ_1068 {
    static List<Integer>[] tree;
    static boolean[] visited;
    static int answer = 0;
    static int delNum;
    static int root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        int[] p = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            p[i] = Integer.parseInt(st.nextToken());
            if (p[i] != -1){
                tree[i].add(p[i]);
                tree[p[i]].add(i);
            }
            else{
                root = i;
            }
        }

        delNum = Integer.parseInt(br.readLine());

        if (delNum == root){
            System.out.println(0);
        }
        else {
            DFS(root);
            System.out.println(answer);
        }
    }

    static void DFS(int num){
        visited[num] = true;
        int cNode = 0;
        for (int i:tree[num]){
            if (!visited[i] && i != delNum){
                cNode++;
                DFS(i);
            }
        }
        if (cNode == 0){
            answer++;
        }
    }
}
