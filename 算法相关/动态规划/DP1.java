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
	 * ��Ŀ����
	 * ����һ���ַ���s���ָ�sʹ�÷ָ����ÿһ���Ӵ����ǻ��Ĵ� ���������Ҷ������������ �ַ��� ��ȣ�
	 * ���㽫�ַ���s�ָ�ɻ��ķָ�������С�и��� ����:�����ַ���s="aab",
	 * ����1����Ϊ���ķָ���["aa","b"]���и�һ�����ɵġ�
	 * 
	 * ************************************
	 * ��̬�滮����˼·
	 * ״̬��
	 * f(x) = s.substring(0, x+1) ������Ӵ�Ҫ �������� �ָ����С����
	 * s.substring(0, x+1) ��Ϊ�±�0,1,2....x ���Ӵ���java�﷨subString x+1 ������
	 * 
	 * ���ƹ�ʽ��
	 * f(x) = min{f(x1)+1, f(x2)+1 ......., f(xn)+1}
	 * x1,x2 .... xn �������һ�ο��ָܷ���±꣬
	 * ���� s=aabcc
	 * ���һ�ηָ�� �����һ���ַ�������Ϊ c cc  ��x1=3  x2=2 ֻ�����������
	 * 
	 * �߽�ֵ����ʼ����
	 * f(0) = 0
	 * f(-1) = -1
	 * 
	 * ����˳��
	 * ���� s=aabcc
	 * f(0)=0 f(1) f(2) f(3) f(4)
	 * 
	 * f(4) ��Ϊ�����ֵ
	 */
	public int minCut(String s) {
		int strSize = s.length();
		List<Integer> f = new ArrayList<>();
		// ���ñ߽�ֵ f(0)=0 f(-1)=-1
		f.add(0);

		for (int i = 1; i < strSize; i++) {
			String s1 = s.substring(0, i + 1);
			f.add(getMinSplitAmount(f, s1));
		}
		return f.get(strSize - 1);
	}

	// ��ȡ�ָ��Ӵ���Ϊÿ�����Ĵ�����С�ָ����
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

	// ��ȡ�Ӵ����һ�ηָ����п��ܵ��±�
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

	// �ж��Ƿ�������� �������ҿ� �� �������� ��һ���ַ���
	public boolean isHuiWenShu(String s) {
		String s1 = "";
		for (int i = s.length() - 1; i >= 0; i--) {
			s1 += s.charAt(i);
		}

		return s.equals(s1) ? true : false;
	}
}
