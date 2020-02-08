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
	 * ��Ŀ����
	 * ����һ���ַ���s��һ�鵥��dict���ж�s�Ƿ�����ÿո�ָ��һ���������У�ʹ�õ������������еĵ��ʶ���dict�еĵ��ʣ����п��԰���һ���������ʣ���
	 * ����: ����s=��leetcode���� dict=["leet", "code"]. ����true����Ϊ"leetcode"���Ա��ָ��"leet".
	 * 
	 * 
	 * ============================
	 * 
	 * ȷ��״̬ f(x) = s.subString(0, x+1) �����ַ��� �ܷ񱻷ָ�ɷ������� true �� false ����
	 * 
	 * ״̬ת�� ���ж�dict.contains(s.subString(0.subString(0, x+1))) �Ƿ�Ϊtrue f(x) = OR{
	 * f(i) and dict.contains(s.subString(i+1, x+1)} ����0<= i < x
	 * 
	 * �߽�ֵ f(0) = dict.contains(s.substring(0, 0 + 1))
	 * 
	 * ����˳�� f(0) f(1) f(2) f(3) .... f(s.length()-1)
	 * 
	 * f(s.length()-1) ��Ϊ��
	 */
	public boolean wordBreak(String s, Set<String> dict) {
		boolean[] f = new boolean[s.length()];
		// ������s.charAt(0) ����char ���ƣ� ����set<String> ���½������false
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
