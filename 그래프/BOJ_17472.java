package 그래프;

import java.io.*;
import java.util.*;

public class BOJ_17472 {
    static int N, M;
    static int[][] myMap;
    static boolean[][] visited;
    static int[] dr = {0, 1, 0, -1}; // 행 이동 방향 (오른쪽, 아래, 왼쪽, 위)
    static int[] dc = {1, 0, -1, 0}; // 열 이동 방향 (오른쪽, 아래, 왼쪽, 위)
    static int sNum = 1;
    static List<List<int[]>> sumlist = new ArrayList<>();
    static List<int[]> mlist = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        myMap = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                myMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (myMap[i][j] != 0 && !visited[i][j]) {
                    List<int[]> tempList = BFS(i, j);
                    sNum++;
                    sumlist.add(new ArrayList<>(tempList));
                }
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (List<int[]> now : sumlist) {
            for (int[] temp : now) {
                int r = temp[0];
                int c = temp[1];
                int now_S = myMap[r][c];

                for (int d = 0; d < 4; d++) {
                    int tempR = dr[d];
                    int tempC = dc[d];
                    int blength = 0;

                    while (r + tempR >= 0 && r + tempR < N && c + tempC >= 0 && c + tempC < M) {
                        if (myMap[r + tempR][c + tempC] == now_S) {
                            break;
                        } else if (myMap[r + tempR][c + tempC] != 0) {
                            if (blength > 1) {
                                pq.add(new int[]{blength, now_S, myMap[r + tempR][c + tempC]});
                            }
                            break;
                        } else {
                            blength++;
                        }

                        if (tempR < 0) tempR -= 1;
                        else if (tempR > 0) tempR += 1;
                        else if (tempC < 0) tempC -= 1;
                        else if (tempC > 0) tempC += 1;
                    }
                }
            }
        }

        int[] parent = new int[sNum];
        for (int i = 0; i < sNum; i++) {
            parent[i] = i;
        }

        int useEdge = 0;
        int result = 0;

        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int v = edge[0];
            int s = edge[1];
            int e = edge[2];

            if (find(s, parent) != find(e, parent)) {
                union(s, e, parent);
                result += v;
                useEdge++;
            }
        }

        if (useEdge == sNum - 2) {
            System.out.println(result);
        } else {
            System.out.println(-1);
        }
    }

    static List<int[]> BFS(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        mlist.clear();
        queue.add(new int[]{i, j});
        mlist.add(new int[]{i, j});
        visited[i][j] = true;
        myMap[i][j] = sNum;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];

            for (int d = 0; d < 4; d++) {
                int tempR = dr[d];
                int tempC = dc[d];

                while (r + tempR >= 0 && r + tempR < N && c + tempC >= 0 && c + tempC < M) {
                    if (!visited[r + tempR][c + tempC] && myMap[r + tempR][c + tempC] != 0) {
                        addNode(r + tempR, c + tempC, queue);
                    } else {
                        break;
                    }
                    if (tempR < 0) tempR -= 1;
                    else if (tempR > 0) tempR += 1;
                    else if (tempC < 0) tempC -= 1;
                    else if (tempC > 0) tempC += 1;
                }
            }
        }
        return new ArrayList<>(mlist);
    }

    static void addNode(int i, int j, Queue<int[]> queue) {
        myMap[i][j] = sNum;
        visited[i][j] = true;
        int[] temp = {i, j};
        mlist.add(temp);
        queue.add(temp);
    }

    static int find(int a, int[] parent) {
        if (a == parent[a]) {
            return a;
        } else {
            return parent[a] = find(parent[a], parent);
        }
    }

    static void union(int a, int b, int[] parent) {
        a = find(a, parent);
        b = find(b, parent);
        if (a != b) {
            parent[b] = a;
        }
    }
}
