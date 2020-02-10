package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *  ��Ŀ����
 * ����һ���ַ���s��һ�鵥��dict����s����ӿո�s���һ�����ӣ�ʹ�þ����е�ÿһ�����ʶ���dict�еĵ��� �������п��ܵĽ��
 * ���磺�������ַ���s *"catsanddog", dict =["cat", "cats", "and", "sand", "dog"]. * *
 * ���صĽ��Ϊ["cats and dog", "cat sand dog"]. *
 * 
 * ==================
 * ����˼·��
 * f(s) = s �ַ������з�������ķָʽ  
 *    ���ؽ��Ϊ ArrayList<String> 
 * 
 *  ���ƹ�ʽ
 *  f(s) = [f(s.substring(0, i1 + 1)) ѭ��ÿ��Ԫ�ؼ��� i1, f(s.substring(0, i2 + 1)) ѭ��ÿ��Ԫ�ؼ��� i2,..... f(s.substring(0, in + 1)) ѭ��ÿ��Ԫ�ؼ��� in,]
 *  in �������� -1< = in < s.length()-1 ������ dict contains s.substring(in + 1, s.length())
 * 
 *  ��ֹ����������Ҫ��֮ǰд�˺ܶ�����ֹ������
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
		// ����ĵݹ���ֹ�����ж��Ǵ����
		ArrayList<String> SBreakIndex = new ArrayList<>();
		if (s.length() == 0) {
			SBreakIndex.add("");
			return SBreakIndex;
		}
		for (int i = s.length() - 2; i >= -1; i--) {
			String s1 = s.substring(i + 1, s.length());
			if (dict.contains(s1)) {
				// s.substring(0, 0) ���ؿ�
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
	 *s �ο����� �����ʽ��Ҳ���õݹ飬Ϊ�˱����ظ����㣬���ö�̬�滮��˼��
	 */
/*	public class Demo1{
	    
	     * ��̬�滮˼�룬��map���Ѿ���õĽ���������������ظ��Ͷ�
	     
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
