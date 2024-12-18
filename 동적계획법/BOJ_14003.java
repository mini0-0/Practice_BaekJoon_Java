package 동적계획법;

import java.io.*;
import java.util.*;

public class BOJ_14003 {
    static int N, maxLength;
    static int B[] = new int[1000001];
    static int A[] = new int[1000001];
    static int D[] = new int[1000001];
    static int ans[] = new int[1000001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int index;
        B[++maxLength] = A[1];
        D[1] = 1;
        for (int i=2; i <= N; i++) {
            if (B[maxLength] < A[i]) {
                B[++maxLength] = A[i];
                D[i] = maxLength;
            }
            else{
                index = binarysearch(1, maxLength, A[i]);
                B[index] = A[i];
                D[i] = index;
            }
        }

        System.out.println(maxLength);
        index = maxLength;
        int x = B[maxLength] + 1;
        for (int i=N; i >=1; i--){
            if (D[i] == index && A[i] < x){
                ans[index] = A[i];
                x = A[i];
                index--;
            }
        }


        for (int i=1; i<=maxLength; i++){
            System.out.print(ans[i] + " ");
        }

    }
    static int binarysearch(int l, int r, int now) {
        int mid;
        while (l < r) {
            mid = (l + r) / 2;
            if (B[mid] < now)
                l = mid + 1;
            else
                r = mid;
        }
        return l;
    }
}
