package test;

import java.util.ArrayList;
import java.util.List;

public class DP1 {
	public static void main(String[] args) {
		String s = "aadaaddcddaaaaaaa";
		int a = new DP1().minCut(s);
		System.out.println(a);
	}

	/**
	 * 题目描述
	 * 给出一个字符串s，分割s使得分割出的每一个子串都是回文串 （从左往右读，从右往左读 字符串 相等）
	 * 计算将字符串s分割成回文分割结果的最小切割数 例如:给定字符串s="aab",
	 * 返回1，因为回文分割结果["aa","b"]是切割一次生成的。
	 * 
	 * ************************************
	 * 动态规划解题思路
	 * 状态：
	 * f(x) = s.substring(0, x+1) 代表的子串要 符合题意 分割的最小次数
	 * s.substring(0, x+1) 即为下标0,1,2....x 的子串，java语法subString x+1 不包括
	 * 
	 * 递推公式：
	 * f(x) = min{f(x1)+1, f(x2)+1 ......., f(xn)+1}
	 * x1,x2 .... xn 代表最后一次可能分割的下标，
	 * 比如 s=aabcc
	 * 最后一次分割后 的最后一个字符串可能为 c cc  则x1=3  x2=2 只有这两种情况
	 * 
	 * 边界值，初始化：
	 * f(0) = 0
	 * f(-1) = -1
	 * 
	 * 计算顺序
	 * 比如 s=aabcc
	 * f(0)=0 f(1) f(2) f(3) f(4)
	 * 
	 * f(4) 即为所求的值
	 */
	public int minCut(String s) {
		int strSize = s.length();
		List<Integer> f = new ArrayList<>();
		// 设置边界值 f(0)=0 f(-1)=-1
		f.add(0);

		for (int i = 1; i < strSize; i++) {
			String s1 = s.substring(0, i + 1);
			f.add(getMinSplitAmount(f, s1));
		}
		return f.get(strSize - 1);
	}

	// 获取分割子串成为每个回文串的最小分割次数
	public int getMinSplitAmount(List<Integer> f, String s) {
		List<Integer> lastSplitIndex = getStrLastSplitIndex(s);
		int minSplitAmount = Integer.MAX_VALUE;
		for (int i = 0; i < lastSplitIndex.size(); i++) {
			if (lastSplitIndex.get(i) < 0) {
				minSplitAmount = Math.min(minSplitAmount, 0);
				continue;
			}
			minSplitAmount = Math.min(minSplitAmount, f.get(lastSplitIndex.get(i)) + 1);
		}
		return minSplitAmount;
	}

	// 获取子串最后一次分割所有可能的下标
	public List<Integer> getStrLastSplitIndex(String s) {
		int strSize = s.length();
		List<Integer> lastSplitIndex = new ArrayList<Integer>();
		for (int i = 1; i < strSize + 1; i++) {
			String s1 = s.substring(strSize - i, strSize);
			if (isHuiWenShu(s1)) {
				lastSplitIndex.add(strSize - i - 1);
			}
		}
		return lastSplitIndex;
	}

	// 判断是否回文数， 从左往右看 和 从右往左看 是一个字符串
	public boolean isHuiWenShu(String s) {
		String s1 = "";
		for (int i = s.length() - 1; i >= 0; i--) {
			s1 += s.charAt(i);
		}

		return s.equals(s1) ? true : false;
	}
}
