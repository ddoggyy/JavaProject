package ex06_06;

public class AutoBoxingUnBoxingEx {
	public static void main(String[] args) {
		int n = 10;
		Integer intObject = n; // 오토 박싱
		System.out.println("intObject = " + intObject);
		
		int m = intObject + 10; // 오토 언박싱
		System.out.println("m = " + m);
	}
}
