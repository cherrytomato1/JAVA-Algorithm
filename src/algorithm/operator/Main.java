package algorithm.operator;

public class Main {
	public static void main(String[] args) {
//		int i = (int)Math.pow(10,9) * 2;
//		printBit(i);
//		int k = i << 1;
//		printBit(k);
//		int l = i * 2;
//		printBit(l);
//
//		int val1 = -16;
//		int val2 = val1 >> 2;
//		printBit(val2);
//		int val3 = val1 >>> 2;
//		printBit(val3);

//		printBit(3);
//		printBit(5);
//		printBit(7);
//
//		System.out.println(3^5);
//		System.out.println(3^7);
//		System.out.println(5^7);


//		printBit((5 << 1) - 3);
//		System.out.println((3 + 5)^7);
//		for (int i = 1; i < 10 ; i++) {
////			printBit(i);
//			for (int j = i + 1; j < 10 ; j++) {
//				System.out.println(i + " ^ " + j + " = " + (i ^ j));
//				printBit(i ^ j);
//			}
//		}

//		System.out.println(5 <<);

		System.out.println(3 ^ 5 ^ 7);
		for (int i = 1; i < 10 ; i++) {
			printBit(i);
			printBit(i^ (i + 1));
		}
//		printBit(3 ^ 5);

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
