package 그래프;

import java.util.*;
import java.io.*;

public class BOJ_1219 {
    static class Edge{
        int start, end, price;

        Edge(int start, int end, int price) {
            this.start = start;
            this.end = end;
            this.price = price;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int sCity = Integer.parseInt(st.nextToken());
        int eCity = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Edge> edges = new ArrayList<Edge>();
        long[] dist = new long[N+1];
        Arrays.fill(dist, -Long.MAX_VALUE);

        for (int i=0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            edges.add(new Edge(start, end, price));
        }


        long[] cityMoney = new long[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i < N; i++){
            cityMoney[i] = Long.parseLong(st.nextToken());
        }

        dist[sCity] = cityMoney[sCity];

        for(int i=0; i < N+100; i++){
            for (Edge edge : edges){
                if (dist[edge.start] == -Long.MAX_VALUE) continue;
                if (dist[edge.start] == Long.MAX_VALUE){
                    dist[edge.end] = Long.MAX_VALUE;
                }
                else if (dist[edge.end] < dist[edge.start] + cityMoney[edge.end] - edge.price){
                    dist[edge.end] = dist[edge.start] + cityMoney[edge.end] - edge.price;
                    if (i >= N-1){
                        dist[edge.end] = Long.MAX_VALUE;
                    }
                }
            }
        }

        if (dist[eCity] == -Long.MAX_VALUE) {
            System.out.println("gg");
        } else if (dist[eCity] == Long.MAX_VALUE) {
            System.out.println("Gee");
        } else {
            System.out.println(dist[eCity]);
        }
    }
}
