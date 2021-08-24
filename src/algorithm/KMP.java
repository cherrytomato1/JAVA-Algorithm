package algorithm;

import java.util.ArrayList;
import java.util.List;

public class KMP {
	private static List<Integer> KMP(String targetString, String searchString) {
		List<Integer> matchedStartIndexList =  new ArrayList<>();
		//실패함수 만들기(접두사 접미사 카운터 배열)
		int[] failArray = getFailArray(searchString);
		//시작 위치 및 같은 개수
		int begin = 0;
		int matched = 0;
		//각 문자열 길이
		int targetLength = targetString.length();
		int searchLength = searchString.length();

		//시작 위치가 목표 문자열 길이 - 검색할 문자열 길이보다 작거나 같을 때 반복
		while (begin <= targetLength - searchLength) {
			//시작 위치 + 일치한 개수의 문자(접미사) == 일치한 개수의 문자(접두사)가 같은 경우
			if (targetString.charAt(begin + matched) == searchString.charAt(matched)) {
				//같은 문자열의 개수가 검색할 문자열의 길이와 같을 때 true
				if (++matched == searchLength) {
					matchedStartIndexList.add(begin);
				}
				continue;
			}
			//일치하는 문자열이 하나도 없을 경우
			if (matched == 0) {
				begin++;
				continue;
			}
			//일치하는 문자열이 있으나, 현재 문자는 일치하지 않는경우
			//시작 위치를 건너뜀, 현재 일치한 갯수 - 실패 함수에서 현재 실패한 갯수의 인덱스( -1)
			begin += matched - failArray[matched - 1];
			//현재 일치한 개수는 실패함수에서 현재 일치한 개수의 인덱스의 값
			matched = failArray[matched - 1];
		}

		return matchedStartIndexList;
	}

	/*
		실패함수 구하기
		각 배열의 인덱스까지만 비교했을 때 ( 해당 위치까지만 문자열이라고 할 때 )
		해당 인덱스에서 접두사와 접미사가 일치하는 개수를 저장
		실패함수도 각각의 실패함수 배열을 통해서 구함
	 */
	private static int[] getFailArray(String searchString) {
		//실패 함수 배열
		int[] ret = new int[searchString.length()];

		//비교 시작 위치, 일치 개수
		int begin = 1;
		int matched = 0;

		//비교 시작 위치 + 일치 개수가 문자열 길이보다 짧은 경우 반복
		while (begin + matched < ret.length) {
			//시작 위치 + 일치한 개수의 문자(접미사) == 일치한 개수의 문자(접두사)가 같은 경우
			if (searchString.charAt(begin + matched) == searchString.charAt(matched)) {
				//현재 비교하는 실패함수 접미사 자리에 일치한 갯수를 넣음(개수면서, 다음에 사용할 인덱스이므로 ++)
				ret[begin + matched] = ++matched;
				continue;
			}
			//접두사와 접미사가 하나도 일치하지 않은 경우
			if (matched == 0) {
				//비교 시작 위치 변경
				begin++;
				continue;
			}
			//일치하지 않았을 경우 시작 위치를 건너뜀
			//건너 뛰는 자리는 현재까지 일치한 개수 - 실패함수에서 해당 일치 인덱스( -1 )의 값(접두사, 접미사가 일치한 개수)
			begin += matched - ret[matched - 1];
			//일치한 개수 = 해당 자리 인덱스( -1 )에서 일치한 개수
			matched = ret[matched - 1];
		}
		return ret;
	}
}
