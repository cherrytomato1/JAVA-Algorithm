package algorithm.operator;

public class Main {
	public static void main(String[] args) {
		int i = (int)Math.pow(10,9) * 2;
		printBit(i);
		int k = i << 1;
		printBit(k);
		int l = i * 2;
		printBit(l);

		int val1 = -16;
		int val2 = val1 >> 2;
		printBit(val2);
		int val3 = val1 >>> 2;
		printBit(val3);
	}
	private static void printBit(int num) {
		System.out.println(num);
		for (int i = 31; ; i--) {
			System.out.print((num & 1 << i) != 0 ? 1 : 0);
			if (i == 0) {
				break;
			}
			if (i % 4 == 0) {
				System.out.print("_");
			}
		}
		System.out.println();
	}
}
