package 트리;

import java.io.*;
import java.util.*;

public class BOJ_11725_ver2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 문자열을 저장할 HashSet
        HashSet<String> textList = new HashSet<>();

        // N개의 문자열 입력받아 HashSet에 저장
        for (int i = 0; i < n; i++) {
            textList.add(br.readLine().trim());
        }

        int count = 0;

        // M개의 문자열을 검색하여 set에 존재하는지 확인
        for (int i = 0; i < m; i++) {
            String text = br.readLine().trim();
            if (textList.contains(text)) {
                count++;
            }
        }

        System.out.println(count);
    }
}
