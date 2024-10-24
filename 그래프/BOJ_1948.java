package 그래프;

import java.io.*;
import java.util.*;

public class BOJ_1948{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<List<int[]>> A = new ArrayList<>();
        List<List<int[]>> reverse_A = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            A.add(new ArrayList<>());
            reverse_A.add(new ArrayList<>());
        }

        int[] indegree = new int[N+1];
        int[] result = new int[N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            A.get(S).add(new int[]{E, V});
            reverse_A.get(E).add(new int[]{S, V});
            indegree[E]++;
        }

        st = new StringTokenizer(br.readLine());
        int startDosi = Integer.parseInt(st.nextToken());
        int endDosi = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();
        q.add(startDosi);

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int[] next : A.get(now)) {
                indegree[next[0]]--;
                result[next[0]] = Math.max(result[next[0]], result[now] + next[1]);
                if (indegree[next[0]] == 0) {
                    q.add(next[0]);
                }
            }
        }

        int cnt = 0;
        boolean[] visited = new boolean[N + 1];
        q.clear();
        q.add(endDosi);
        visited[endDosi] = true;

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int[] next : reverse_A.get(now)) {
                if (result[next[0]] + next[1] == result[now]) {
                    cnt++;
                    if (!visited[next[0]]) {
                        visited[next[0]] = true;
                        q.add(next[0]);
                    }
                }
            }
        }

        System.out.println(result[endDosi]);
        System.out.println(cnt);
    }
}
