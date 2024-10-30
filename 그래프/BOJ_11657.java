package 그래프;
import java.io.*;
import java.util.*;

public class BOJ_11657 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 에지리스트
        Edge[] edges = new Edge[M + 1];
        // 정답배열(최단거리 배열)
        long[] D = new long[N + 1];
        Arrays.fill(D, Integer.MAX_VALUE);//dist 배열의 모든 요소를 Integer.MAX_VALUE로 초기화

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(A, B, C);
        }

        // 벨만 포드
        D[1] = 0; // 시작노드는 0
        for (int i = 1; i < N; i++) { // 업데이트 반복 횟수: N-1
            for (int j = 0; j < M; j++) {
                Edge edge = edges[j];
                if (D[edge.start] != Integer.MAX_VALUE && D[edge.start] + edge.cost < D[edge.end]) {
                    D[edge.end] = D[edge.start] + edge.cost; // 업데이트
                }
            }
        }

        // 음수 사이클 판별 ( 한번 더 수행 )
        boolean check = false;
        for (int i = 0; i < M; i++) {
            Edge edge = edges[i];
            if(D[edge.start] != Integer.MAX_VALUE && D[edge.start] + edge.cost < D[edge.end]){
                check = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (check) { //음수 사이클이 존재한다면
            sb.append("-1");
        } else { //음수 사이클이 존재하지 않다면
            //D[1]은 0이므로 2부터 시작
            for (int i = 2; i <= N; i++) {
                if (D[i] == Integer.MAX_VALUE) {
                    sb.append("-1").append("\n");
                } else {
                    sb.append(D[i]).append("\n");
                }
            }
        }
        System.out.println(sb);
    }

    static class Edge {
        int start;
        int end;
        int cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}