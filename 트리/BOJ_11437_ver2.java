package 트리;

import java.io.*;
import java.util.*;

public class BOJ_11437_ver2 {
    static int N, log;
    static ArrayList<Integer>[] tree;
    static int[][] parent;
    static int[] depth;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        log = (int) (Math.log(N) / Math.log(2)) + 1;

        tree = new ArrayList[N + 1];
        parent = new int[N + 1][log];
        depth = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            tree[s].add(e);
            tree[e].add(s);
        }

        BFS(1);

        // 모든 노드에 대해 2^i 번째 부모 미리 계산 (sparse table)
        for (int i = 1; i < log; i++) {
            for (int j = 1; j <= N; j++) {
                if (parent[j][i - 1] != 0) {
                    parent[j][i] = parent[parent[j][i - 1]][i - 1];
                }
            }
        }

        // LCA 질의 처리
        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(excuteLCA(a, b)).append("\n");
        }
        System.out.print(sb);
    }

    // BFS로 깊이와 첫 번째 부모 저장
    public static void BFS(int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node] = true;

        while (!queue.isEmpty()) {
            int nowNode = queue.poll();
            for (int nextNode : tree[nowNode]) {
                if (!visited[nextNode]) {
                    visited[nextNode] = true;
                    queue.add(nextNode);
                    parent[nextNode][0] = nowNode; // 2^0 번째 부모
                    depth[nextNode] = depth[nowNode] + 1;
                }
            }
        }
    }

    // LCA 함수 (binary lifting 기법)
    public static int excuteLCA(int a, int b) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        // 깊이를 맞추기
        for (int i = log - 1; i >= 0; i--) {
            if (depth[a] - depth[b] >= (1 << i)) {
                a = parent[a][i];
            }
        }

        if (a == b) return a;

        // 공통 조상 찾기
        for (int i = log - 1; i >= 0; i--) {
            if (parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        return parent[a][0];
    }
}
