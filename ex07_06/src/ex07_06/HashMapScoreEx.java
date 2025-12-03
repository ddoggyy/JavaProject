package ex07_06;

import java.util.*;

public class HashMapScoreEx {
	public static void main(String[] args) {
		HashMap<String, Integer> javaScore = new HashMap<String, Integer>();
		
		javaScore.put("김은비", 97);
		javaScore.put("하여린", 88);
		javaScore.put("전아린", 98);
		javaScore.put("이동건", 70);
		javaScore.put("양승연", 99);

		System.out.println("HashMap의 요소 개수:" 
				+ javaScore.size());
		
		Set<String> keys = javaScore.keySet();

		// key 문자열을 순서대로 접근할 수 있는 Iterator 리턴
		Iterator<String> it = keys.iterator(); 
		while(it.hasNext()) {
			String name = it.next();
			int score = javaScore.get(name);
			System.out.println(name + " : " + score);
		}
	}
}

