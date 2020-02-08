package test;

import java.util.HashSet;
import java.util.Set;

public class DP2 {
	public static void main(String[] args) {
		String s = "leetcode";
		Set<String> dict = new HashSet<>();
		dict.add("leet");
		dict.add("code");
		boolean a = new DP2().wordBreak(s, dict);
		System.out.println(a);
	}

	/**
	 * 题目描述
	 * 给定一个字符串s和一组单词dict，判断s是否可以用空格分割成一个单词序列，使得单词序列中所有的单词都是dict中的单词（序列可以包含一个或多个单词）。
	 * 例如: 给定s=“leetcode”； dict=["leet", "code"]. 返回true，因为"leetcode"可以被分割成"leet".
	 * 
	 * 
	 * ============================
	 * 
	 * 确定状态 f(x) = s.subString(0, x+1) 代表字符串 能否被分割成符合题意 true 能 false 不能
	 * 
	 * 状态转移 先判断dict.contains(s.subString(0.subString(0, x+1))) 是否为true f(x) = OR{
	 * f(i) and dict.contains(s.subString(i+1, x+1)} 其中0<= i < x
	 * 
	 * 边界值 f(0) = dict.contains(s.substring(0, 0 + 1))
	 * 
	 * 计算顺序 f(0) f(1) f(2) f(3) .... f(s.length()-1)
	 * 
	 * f(s.length()-1) 即为答案
	 */
	public boolean wordBreak(String s, Set<String> dict) {
		boolean[] f = new boolean[s.length()];
		// 不能用s.charAt(0) 返回char 类似， 但是set<String> 导致结果返回false
		f[0] = dict.contains(s.substring(0, 0 + 1));
		for (int i = 1; i < s.length(); i++) {
			String s1 = s.substring(0, i + 1);
			if (dict.contains(s1)) {
				f[i] = true;
				continue;
			}
			f[i] = false;
			for (int j = 0; j < s1.length(); j++) {
				String s2 = s1.substring(j + 1, i + 1);
				boolean isJStringCanSplit = (f[j] && dict.contains(s2));
				if (isJStringCanSplit) {
					f[i] = true;
					break;
				}
			}
		}
		return f[s.length() - 1];
	}

}
