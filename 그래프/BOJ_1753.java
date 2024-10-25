package 그래프;

import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class BOJ_1753 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken()); // 정점 수
        int E = Integer.parseInt(st.nextToken()); // 간선 수
        int K = Integer.parseInt(br.readLine());  // 시작 정점

        List<List<Node>> A = new ArrayList<>();   // 인접 리스트
        for (int i = 0; i <= V; i++) {
            A.add(new ArrayList<>());
        }

        int[] dist = new int[V + 1];              // 거리 배열
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[V + 1];   // 방문 여부 배열

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            A.get(u).add(new Node(v, w));
        }

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(K, 0));
        dist[K] = 0;

        while (!q.isEmpty()) {
            Node now = q.poll();
            int now_v = now.vertex;

            if (visited[now_v]) continue;
            visited[now_v] = true;

            for (Node next : A.get(now_v)) {
                if (dist[next.vertex] > dist[now_v] + next.weight) {
                    dist[next.vertex] = dist[now_v] + next.weight;
                    q.add(new Node(next.vertex, dist[next.vertex]));
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i <= V; i++) {
            if (visited[i]) {
                bw.write(dist[i] + "\n");
            } else {
                bw.write("INF\n");
            }
        }
        bw.flush();
        bw.close();
    }

    static class Node implements Comparable<Node> {
        int vertex;
        int weight;

        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
