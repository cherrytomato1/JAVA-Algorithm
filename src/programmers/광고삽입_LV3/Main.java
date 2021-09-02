package programmers.광고삽입_LV3;

import java.util.*;

public class Main {

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
		private long[] timeSum;

		private Queue<Integer> startPq;
		private Queue<Integer> endPq;

		public String solution(String play_time, String adv_time, String[] logs) {

			advTime = timeStringToSeconds(adv_time);
			totalTime = timeStringToSeconds(play_time);

			startPq = new PriorityQueue<>();
			endPq = new PriorityQueue<>();

			logsToPq(logs);

			viewTime = new int[totalTime + 1];
			timeSum = new long[totalTime + 1];

			setViewTime();

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

		private void logsToPq(String[] logs) {
			for (String log : logs) {
				String[] logToken = log.split("-");
				startPq.offer(timeStringToSeconds(logToken[0]));
				endPq.offer(timeStringToSeconds(logToken[1]));
			}
		}

		private void setViewTime() {
			int viewer = 0;
			for (int i = 0; i <= totalTime; i++) {
				while (!startPq.isEmpty() && startPq.peek() <= i) {
					startPq.poll();
					viewer++;
				}

				while (!endPq.isEmpty() && endPq.peek() <= i) {
					endPq.poll();
					viewer--;
				}
				viewTime[i] = viewer;
			}
		}

		private int getMaxViewTimeIndex() {
			long maxViewTime = 0;
			int maxViewIndex = 0;

			timeSum[totalTime] = viewTime[totalTime];
			for (int i = totalTime - 1; i > totalTime - advTime; i--) {
				timeSum[i] += viewTime[i] + timeSum[i + 1];

				if (maxViewTime > timeSum[i]) {
					continue;
				}
				maxViewIndex = i;
				maxViewTime = timeSum[i];
			}
			for (int i = totalTime - advTime; i >= 0; i--) {
				timeSum[i] += viewTime[i] + timeSum[i + 1] - viewTime[i + advTime];

				if (maxViewTime > timeSum[i]) {
					continue;
				}
				maxViewIndex = i;
				maxViewTime = timeSum[i];

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
