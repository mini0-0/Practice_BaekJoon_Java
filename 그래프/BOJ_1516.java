import java.io.*;
import java.util.*;

public class BOJ_1516 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<List<Integer>> A = new ArrayList<>();
        for (int i = 0; i < N+1; i++) {
            A.add(new ArrayList<>());
        }

        int[] indegree = new int[N+1];
        int[] build = new int[N+1];
        int[] result = new int[N+1];

        for (int i = 1; i < N+1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            build[i] = Integer.parseInt(st.nextToken());

            while (true) {
                int preTemp = Integer.parseInt(st.nextToken());
                if(preTemp == -1){
                    break;
                }
                A.get(preTemp).add(i);
                indegree[i]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i < N+1; i++) {
            if(indegree[i] == 0){
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next: A.get(now)) {
                indegree[next] -= 1;
                result[next] =  Math.max(result[next], result[now] + build[now]);
                if (indegree[next] == 0){
                    q.add(next);
                }
            }

        }

        for (int i = 1; i < N+1; i++) {
            System.out.println(result[i] + build[i]);
        }
    }
}
