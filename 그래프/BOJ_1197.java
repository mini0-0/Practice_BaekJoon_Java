package 그래프;

import java.util.*;
import java.io.*;

public class BOJ_1197 {
    static int N, M;
    static PriorityQueue<Edge> pq;
    static int[] parent;

    static class Edge implements Comparable<Edge> {
        int start, end, weight;
        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();
        parent = new int[N + 1];

        for (int i=0; i <= N; i++ ){
            parent[i] = i;
        }

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.add(new Edge(s, e, w));
        }

        int useEdge = 0;
        int result = 0;

        while (useEdge < N-1 && !pq.isEmpty()){
            Edge edge = pq.poll();
            if (find(edge.start) != find(edge.end)){
                union(edge.start, edge.end);
                result += edge.weight;
                useEdge++;
            }
        }

        System.out.println(result);
    }
    static int find(int a){
        if (a == parent[a]) return a;
        else {
            parent[a] = find(parent[a]);
            return parent[a];
        }
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if (a != b){
            parent[b] = a;
        }
    }
}


