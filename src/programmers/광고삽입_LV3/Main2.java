package programmers.광고삽입_LV3;

import java.util.*;

public class Main2 {

	public static void main(String[] args) {
		Solution solution = new Solution();

		String playTime = "02:03:55";
		String advTime = "00:14:15";
		String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};
		System.out.println(solution.solution(playTime, advTime, logs));
	}

	private static class Solution {

		private int totalTime;
		private int advTime;

		private int[] viewTime;


		public String solution(String play_time, String adv_time, String[] logs) {

			advTime = timeStringToSeconds(adv_time);
			totalTime = timeStringToSeconds(play_time);

			viewTime = new int[totalTime + 1];

			setViewTime(logs);

			return secondsToTimeString(getMaxViewTimeIndex());
		}


		private int timeStringToSeconds(String time) {
			String[] timeToken = time.split(":");

			int ret = 0;
			for (int i = 2; i >= 0; i--) {
				ret += Integer.parseInt(timeToken[i]) * Math.pow(60, 2 - i);
			}
			return ret;
		}

		private void setViewTime(String[] logs) {
			for (String log : logs) {
				String[] logToken = log.split("-");
				viewTime[timeStringToSeconds(logToken[0])]++;
				viewTime[timeStringToSeconds(logToken[1])]--;
			}

			for (int i = 0; i < totalTime; i++) {
				viewTime[i + 1] += viewTime[i];
			}
		}

		private int getMaxViewTimeIndex() {
			long maxViewTime = 0;
			int maxViewIndex = 0;

			long currentTimeSum = 0;

			//i + 1 의 누적합 구하기
			//i번째 친구를 제외하고 i + advTime의 값을 추가
			for (int i = 0; i <= totalTime - advTime; i++) {
				currentTimeSum -= viewTime[i];
				currentTimeSum += viewTime[i + advTime];

				if (maxViewTime >= currentTimeSum) {
					continue;
				}
				maxViewIndex = i + 1;
				maxViewTime = currentTimeSum;

			}
			return maxViewIndex;
		}

		private String secondsToTimeString(int time) {
			StringBuilder sb = new StringBuilder();
			for (int i = 2; i >= 0; i--) {
				int val = (int) Math.pow(60, i);
				sb.append(time / val < 10 ? "0" + time / val : time / val);
				time %= val;

				if (i != 0) {
					sb.append(":");
				}
			}

			return sb.toString();
		}
	}


}
