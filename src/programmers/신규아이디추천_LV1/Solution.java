package programmers.신규아이디추천_LV1;


class Solution {
	public class NewIdBuilder {
		String s;

		public NewIdBuilder(String s) {
			this.s = s;
		}

		public NewIdBuilder toLowerCase() {
			this.s = s.toLowerCase();
			return this;
		}

		public NewIdBuilder dotCheck() {
			this.s = s.replaceAll("[.]{2,}", ".").replaceAll("^[.]|[.]$", "");
			return this;
		}

		public NewIdBuilder charCheck() {
			this.s = s.replaceAll("[^a-z0-9._-]", "");
			return this;
		}

		public NewIdBuilder blankCheck() {
			if (s.isEmpty()) {
				s = "a";
			}
			return this;
		}

		public NewIdBuilder maxLengthCheck() {
			if (s.length() >= 16) {
				s = s.substring(0, 15);
			}
			return this;
		}

		public NewIdBuilder minLengthCheck() {
			while (s.length() < 3) {
				s += s.charAt(s.length() - 1);
			}
			return this;
		}

		@Override
		public String toString() {
			return s;
		}
	}


	public String solution(String new_id) {
		return new NewIdBuilder(new_id).toLowerCase().charCheck().dotCheck()
				       .blankCheck().minLengthCheck().maxLengthCheck().dotCheck().toString();
	}
}

