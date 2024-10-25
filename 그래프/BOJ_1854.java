package 그래프;

import java.io.*;
import java.util.*;

public class BOJ_1854 {
    static int N, M, K;
    static List<List<Node>> graph;
    static PriorityQueue<Integer>[] dist;

    static class Node implements Comparable<Node> {
        int vertex, cost;

        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        dist = new PriorityQueue[N + 1];

        // 그래프와 우선순위 큐 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            dist[i] = new PriorityQueue<>(Collections.reverseOrder());
            for (int j = 0; j < K; j++) {
                dist[i].add(Integer.MAX_VALUE);
            }
        }

        // 간선 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
        }

        dijkstra(1);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i <= N; i++) {
            if (dist[i].peek() == Integer.MAX_VALUE) {
                bw.write("-1\n");
            } else {
                bw.write(dist[i].peek() + "\n");
            }
        }
        bw.flush();
        bw.close();
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start].poll();
        dist[start].add(0);

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentVertex = current.vertex;
            int currentCost = current.cost;

            // 현재 노드의 최단 경로보다 비용이 큰 경우 무시
            if (dist[currentVertex].peek() < currentCost) {
                continue;
            }

            for (Node neighbor : graph.get(currentVertex)) {
                int nextVertex = neighbor.vertex;
                int nextCost = neighbor.cost;
                int totalCost = currentCost + nextCost;

                // K번째 최단 경로보다 작은 경우 업데이트
                if (dist[nextVertex].peek() > totalCost) {
                    dist[nextVertex].poll();
                    dist[nextVertex].add(totalCost);
                    pq.add(new Node(nextVertex, totalCost));
                }
            }
        }
    }
}
