package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *  题目描述
 * 给定一个字符串s和一组单词dict，在s中添加空格将s变成一个句子，使得句子中的每一个单词都是dict中的单词 返回所有可能的结果
 * 例如：给定的字符串s *"catsanddog", dict =["cat", "cats", "and", "sand", "dog"]. * *
 * 返回的结果为["cats and dog", "cat sand dog"]. *
 * 
 * ==================
 * 解题思路：
 * f(s) = s 字符串所有符合题意的分割方式  
 *    返回结果为 ArrayList<String> 
 * 
 *  递推公式
 *  f(s) = [f(s.substring(0, i1 + 1)) 循环每个元素加上 i1, f(s.substring(0, i2 + 1)) 循环每个元素加上 i2,..... f(s.substring(0, in + 1)) 循环每个元素加上 in,]
 *  in 符合条件 -1< = in < s.length()-1 且满足 dict contains s.substring(in + 1, s.length())
 * 
 *  终止条件（很重要，之前写了很多错的终止条件）
 *  s.length()==0, return [""]
 */
public class Recursion2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "catsanddog";
		Set<String> dict = new HashSet<>();
		dict.add("cat");
		dict.add("cats");
		dict.add("and");
		dict.add("sand");
		dict.add("dog");
		ArrayList<String> SBreakIndex = new Recursion2().wordBreakIndex(s, dict);
		for (int i = 0; i < SBreakIndex.size(); i++) {
			System.out.println(SBreakIndex.get(i));
		}
	}

	public ArrayList<String> wordBreakIndex(String s, Set<String> dict) {
		// 上面的递归终止条件判断是错误的
		ArrayList<String> SBreakIndex = new ArrayList<>();
		if (s.length() == 0) {
			SBreakIndex.add("");
			return SBreakIndex;
		}
		for (int i = s.length() - 2; i >= -1; i--) {
			String s1 = s.substring(i + 1, s.length());
			if (dict.contains(s1)) {
				// s.substring(0, 0) 返回空
				String s2 = s.substring(0, i + 1);
				ArrayList<String> IStringBreakIndex = wordBreakIndex(s2, dict);
				for (int j = 0; j < IStringBreakIndex.size(); j++) {
					String trim = IStringBreakIndex.get(j) == "" ? "" : " ";
					SBreakIndex.add(IStringBreakIndex.get(j) + trim + s1);
				}
			}
		}
		return SBreakIndex;
	}
	
	/**
	 *s 参考别人 解决方式，也是用递归，为了避免重复计算，借用动态规划的思想
	 */
/*	public class Demo1{
	    
	     * 动态规划思想，用map把已经求得的结果存起来，避免重复劳动
	     
	    public ArrayList<String> wordBreak(String s, Set<String> wordDict) {
	        return DFS(s, wordDict, new HashMap<String, ArrayList<String>>());
	    }
	 
	    private ArrayList<String> DFS(String s, Set<String> wordDict, HashMap<String, ArrayList<String>> map) {
	        if (map.containsKey(s))
	            return map.get(s);
	        ArrayList<String> res = new ArrayList<String>();
	        if (s.length() == 0){
	            res.add("");
	            return res;
	        }
	        for (String subStr : wordDict) {
	            if (s.startsWith(subStr)) {
	                for (String str : DFS(s.substring(subStr.length()), wordDict, map)) {
	                    res.add(subStr + (str == "" ? "" : " ")+ str);
	 
	                }
	 
	            }
	        }
	        map.put(s, res);
	        return res;
	 
	    }
	}
*/

}
