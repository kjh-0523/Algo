import java.util.*;
import java.io.*;

public class Main {

    public static int N;
    public static HashMap<Character,Node> tree;
    public static ArrayList<Character> ans;

    public static class Node{
        char left, right;
        public Node(char left, char right){
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        tree = new HashMap<>();
        for(int i = 0; i < N; i++){
            String line = br.readLine();
            char p = line.charAt(0);
            char l = line.charAt(2);
            char r = line.charAt(4);
            tree.put(p,new Node(l,r));
        }
        StringBuilder sb = new StringBuilder();
        ans = new ArrayList<>();
        frontDfs('A');
        for(char a : ans){
            sb.append(a);
        }
        sb.append("\n");
        ans = new ArrayList<>();
        middleDfs('A');
        for(char a : ans){
            sb.append(a);
        }
        sb.append("\n");

        ans = new ArrayList<>();
        rearDfs('A');
        for(char a : ans){
            sb.append(a);
        }
        System.out.println(sb.toString());
    }

    public static void frontDfs(char root){
        ans.add(root);
        Node cur = tree.get(root);
        if(cur.left != '.'){
            frontDfs(cur.left);
        }

        if(cur.right != '.'){
            frontDfs(cur.right);
        }
    }

    public static void middleDfs(char root){
        Node cur = tree.get(root);
        if(cur.left != '.'){
            middleDfs(cur.left);
        }

        ans.add(root);

        if(cur.right != '.'){
            middleDfs(cur.right);
        }
    }

    public static void rearDfs(char root){
        Node cur = tree.get(root);
        if(cur.left != '.'){
            rearDfs(cur.left);
        }

        if(cur.right != '.'){
            rearDfs(cur.right);
        }

        ans.add(root);
    }
}
