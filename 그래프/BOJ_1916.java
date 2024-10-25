package 그래프;
import java.io.*;
import java.util.*;

public class BOJ_1916 {
    static int N, M;
    static List<List<Node>> graph;
    static int[] dist;
    static boolean[] visited;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // Number of nodes
        M = Integer.parseInt(br.readLine()); // Number of edges

        graph = new ArrayList<>();
        dist = new int[N + 1];
        visited = new boolean[N + 1];
        Arrays.fill(dist, INF);

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, weight));
        }

        st = new StringTokenizer(br.readLine());
        int startIdx = Integer.parseInt(st.nextToken());
        int endIdx = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra(startIdx, endIdx));
    }

    public static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int now = currentNode.vertex;

            if (visited[now]) continue;
            visited[now] = true;

            for (Node neighbor : graph.get(now)) {
                int next = neighbor.vertex;
                int value = neighbor.weight;

                if (dist[next] > dist[now] + value) {
                    dist[next] = dist[now] + value;
                    pq.add(new Node(next, dist[next]));
                }
            }
        }

        return dist[end];
    }

    static class Node implements Comparable<Node> {
        int vertex, weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.weight, other.weight);
        }
    }
}
