package 트리;

import java.io.*;
import java.util.*;

public class BOJ_11437_ver1 {
    static ArrayList<Integer>[] tree;
    static boolean[] visited;
    static int[] parent;
    static int[] depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N+1];
        visited = new boolean[N+1];
        parent = new int[N+1];
        depth = new int[N+1];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for(int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            tree[s].add(e);
            tree[e].add(s);

        }

        BFS(1);

        int M = Integer.parseInt(br.readLine());
        HashMap<String, Integer> dic = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            String key1 = a + "," + b;
            String key2 = b + "," + a;

            if (!dic.containsKey(key1)) {
                dic.put(key1, excuteLCA(a, b));
                dic.put(key2, dic.get(key1));
            }

            sb.append(dic.get(key1)).append("\n");
        }
        System.out.println(sb);
    }

    public static void BFS(int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node] = true;
        while (!queue.isEmpty()) {
            int nowNode = queue.poll();
            for (int next : tree[nowNode]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    parent[next] = nowNode;
                    depth[next] = depth[nowNode] + 1;

                }
            }
        }
    }

    public static int excuteLCA(int a, int b) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        while (depth[a] != depth[b]) {
            a = parent[a];
        }

        while (a != b) {
            a = parent[a];
            b = parent[b];
        }
        return a;
    }
}
