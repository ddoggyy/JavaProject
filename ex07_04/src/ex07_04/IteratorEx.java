package ex07_04;

import java.util.*;

public class IteratorEx {
	public static void main(String[] args) {
		Vector<Integer> v = new Vector<Integer>(); 
		v.add(5); // 5 삽입
		v.add(4); // 4 삽입
		v.add(-1); // -1 삽입
		v.add(2, 100); // 4와 -1 사이에 정수 100 삽입
	
		// Iterator로 모든 정수 출력
		Iterator<Integer> it = v.iterator(); 
		while(it.hasNext()) {
			int n = it.next();
			System.out.println(n);
		}
		
		int sum = 0;
		it = v.iterator();
		while(it.hasNext()) {
			int n = it.next();
			sum += n; 
		}
		System.out.println("벡터에 있는 정수 합 : " + sum);
	}
}



