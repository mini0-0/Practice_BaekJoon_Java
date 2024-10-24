import java.io.*;
import java.util.*;

public class BOJ_2252 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        List<List<Integer>> A = new ArrayList<>();
        int[] indegree = new int[N+1];

        for (int i=0; i<=N; i++){
            A.add(new ArrayList<>());
        }

        for (int i=0; i<M; i++){
            int s = sc.nextInt();
            int e = sc.nextInt();
            A.get(s).add(e);
            indegree[e]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i=1; i<=N; i++){
            if (indegree[i] == 0){
                q.add(i);
            }
        }

        while (!q.isEmpty()){
            int now = q.poll();
            System.out.print(now + " ");

            for (int next : A.get(now)){
                indegree[next]--;
                if (indegree[next] == 0){
                    q.add(next);
                }
            }
        }
    }
}
