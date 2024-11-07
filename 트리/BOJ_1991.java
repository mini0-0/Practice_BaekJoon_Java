package 트리;

import java.io.*;
import java.util.*;

public class BOJ_1991 {
    static Map<Character, char[]> tree = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char root = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            tree.put(root,new char[] {left,right});
        }

        preOrder('A');
        System.out.println();
        inOrder('A');
        System.out.println();
        postOrder('A');
    }

    public static void preOrder(char now) {
        // root -> left -> right
        if (now == '.') return;
        System.out.print(now+"");
        preOrder(tree.get(now)[0]);
        preOrder(tree.get(now)[1]);

    }

    public static void inOrder(char now) {
        // left -> root -> right
        if (now == '.') return;
        inOrder(tree.get(now)[0]);
        System.out.print(now+"");
        inOrder(tree.get(now)[1]);

    }

    public static void postOrder(char now) {
        // left -> right -> root
        if (now == '.') return;
        postOrder(tree.get(now)[0]);
        postOrder(tree.get(now)[1]);
        System.out.print(now+"");
    }


}
