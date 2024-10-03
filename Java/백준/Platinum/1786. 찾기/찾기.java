import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String p = br.readLine();
		
		List<Integer> ans = kmp(s,p);
		System.out.println(ans.size());
		for(int a : ans) {
			System.out.println(a+1);
		}
	}
	
	static int[] getPi(String p) {
		int n = p.length();
		int[] pi = new int[n];
		int j = 0;
		for (int i = 1; i < n; i++) {
			while(j > 0 && p.charAt(i) != p.charAt(j)) {
				j = pi[j-1];
			}
			if(p.charAt(i) == p.charAt(j)) {
				pi[i] = ++j;
			}
		}
		return pi;
	}
	
	static List<Integer> kmp(String s, String p) {
		List<Integer> ans = new ArrayList<Integer>();
		int[] pi = getPi(p);
		int sl = s.length();
		int pl = p.length();
		int j = 0;
		for (int i = 0; i < sl; i++) {
			while(j > 0 && s.charAt(i) != p.charAt(j)) {
				j = pi[j-1];
			}
			if(s.charAt(i) == p.charAt(j)) {
				if(j == pl-1) {
					ans.add(i-pl+1);
					j = pi[j];
				}else {
					j++;
				}
			}
		}
		return ans;
	}
}