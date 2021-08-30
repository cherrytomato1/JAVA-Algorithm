package programmers.순위검색_LV2;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
		String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
		String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150", "- and - and - and - 5"};

		int[] answer = solution.solution(info, query);

		System.out.println(Arrays.toString(answer));
	}
	static class Solution {
		public int[] solution(String[] info, String[] query) {

			Map<String, List<Integer>> map = setInfoToMap(info);
			sortMap(map);
			int[] answer = new int[query.length];
			for (int i = 0; i < query.length; i++) {
				answer[i] = find(query[i].split(" and "), map);
			}

			return answer;
		}

		public Map<String, List<Integer>> setInfoToMap(String[] infoArray) {
			Map<String, List<Integer>> retMap = new HashMap<>();
			for (int i = 0; i < infoArray.length; i++) {
				String[] info = infoArray[i].split(" ");

				setInfoToMap(info, new StringBuilder(), 0, retMap);
			}

			return retMap;
		}

		public void setInfoToMap(String[] info, StringBuilder sb, int idx, Map<String, List<Integer>> map) {
			if (idx == info.length - 1) {
				if (!map.containsKey(sb.toString())) {
					map.put(sb.toString(), new ArrayList<>());
				}
				map.get(sb.toString()).add(Integer.parseInt(info[info.length - 1]));
				return ;
			}

			setInfoToMap(info, new StringBuilder(sb.toString()).append("-"), idx + 1, map);
			setInfoToMap(info, new StringBuilder(sb.toString()).append(info[idx]), idx + 1, map);
		}

		public void sortMap(Map<String, List<Integer>> map) {
			map.forEach((key, value) ->
				Collections.sort(value)
			);
		}

		public int find(String[] query, Map<String, List<Integer>> map) {
			String[] lastToken = query[query.length - 1].split(" ");
			query[query.length - 1] = lastToken[0];
			int value = Integer.parseInt(lastToken[1]);

			StringBuilder sb = new StringBuilder();
			for (String token : query) {
				sb.append(token);
			}

			List<Integer> list = map.get(sb.toString());
			if (list == null) {
				return 0;
			}
			return list.size() - lowerBound(list, value);
		}

		public int lowerBound(List<Integer> list, int key) {
			int low = 0;
			int high = list.size() - 1;

			while (low <= high) {
				int mid = (low + high) >> 1;
				int midValue = list.get(mid);

				if (midValue < key) {
					low = mid + 1;
					continue;
				}
				high = mid - 1;
			}
			return low;
		}
	}
}
