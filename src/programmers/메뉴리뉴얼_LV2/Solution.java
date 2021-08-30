package programmers.메뉴리뉴얼;

import java.util.*;

class Solution {

	Set<Integer> courseCountSet;

	public String[] solution(String[] orders, int[] course) {
		String[] answer = {};

		Map<String, Integer> courseMap = new HashMap<>();

		courseCountSet = new HashSet<>();

		for (int val : course) {
			courseCountSet.add(val);
		}

		for (int i = 0; i < orders.length; i++) {
			char[] orderArray = orders[i].toCharArray();
			Arrays.sort(orderArray);
			generateCourse(0, 0, 0, orderArray, courseMap);
		}

		List<String> resList = new ArrayList(courseMap.size());

		int[] maxCount = new int[11];

		courseMap.forEach((key, value) -> {
			int len = key.length();
			if (maxCount[len] < value) {
				maxCount[len] = value;
			}
		});

		courseMap.forEach((key, value) -> {
			int len = key.length();
			if (maxCount[len] == value && value != 1) {
				resList.add(key);
			}
		});

		Collections.sort(resList);

		return resList.toArray(new String[resList.size()]);
	}

	public void generateCourse(int idx, int cnt, int flag, char[] s, Map<String, Integer> courseMap) {
		if (idx == s.length) {
			if (courseCountSet.contains(cnt)) {
				offerMap(flag, s, courseMap);
			}
			return;
		}
		generateCourse(idx + 1, cnt + 1, flag | 1 << idx, s, courseMap);
		generateCourse(idx + 1, cnt, flag, s, courseMap);
	}

	public void offerMap(int flag, char[] s, Map<String, Integer> courseMap) {
		StringBuilder res = new StringBuilder();

		for (int i = 0; i < s.length; i++) {
			if ((flag & 1 << i) == 0) {
				continue;
			}
			res.append(s[i]);
		}

		String str = res.toString();

		courseMap.put(str, courseMap.containsKey(str) ? courseMap.get(str) + 1 : 1);
	}
}