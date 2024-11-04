package 그래프;

import java.io.*;
import java.util.*;

public class BOJ_1414 {

    static int N;
    static PriorityQueue<Edge> pq;
    static int[] parent;
    static int sum = 0;

    static class Edge implements Comparable<Edge> {
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }


        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        pq = new PriorityQueue<Edge>();
        parent = new int[N];

        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N; i++) {
            char[] tempC = br.readLine().toCharArray();
            for (int j = 0; j < N; j++){
                int temp = 0;
                if ('a' <= tempC[j] && tempC[j] <= 'z') {
                    temp = tempC[j] - 'a' + 1;
                }
                else if ('A' <= tempC[j] && tempC[j] <= 'Z') {
                    temp = tempC[j] - 'A' + 27;
                }

                sum += temp;
                if (i != j && temp != 0){
                    pq.add(new Edge(i, j, temp));
                }
            }
        }


        int useEdge = 0;
        int result = 0;

        // 크루스칼 알고리즘
        while (!pq.isEmpty() && useEdge < N - 1) {
            Edge edge = pq.poll();
            if (find(edge.start) != find(edge.end)) {
                union(edge.start, edge.end);
                result += edge.weight;
                useEdge++;
            }
        }

        if (useEdge == N - 1) {
            System.out.println(sum - result);
        } else {
            System.out.println(-1);
        }
    }

    // Find 함수 (경로 압축 적용)
    static int find(int a) {
        if (a == parent[a]) {
            return a;
        } else {
            parent[a] = find(parent[a]);
            return parent[a];
        }
    }

    // Union 함수
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }
}