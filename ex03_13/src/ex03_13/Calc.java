package ex03_13;

public class Calc {
	public static void main(String[] args) {
		double sum = 0.0;
		
		for(int i = 0; i < args.length; i++) 
			sum += Double.parseDouble(args[i]); // 문자열을 실수로 변환하여 계산
		
		System.out.println("합계 :" + sum);
	}
}
