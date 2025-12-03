package ex07_08;

import java.util.*;
public class ToeicScoreManager {
	public static void main(String[] args) {
		HashMap< String, Vector<Integer>> map = new HashMap<String, Vector<Integer>>();
		Scanner scanner = new Scanner(System.in);

		map.put("한지운" , new Vector<Integer>());
		map.put("김하진" , new Vector<Integer>());
		map.put("하여린" , new Vector<Integer>());
		map.put("윤단비" , new Vector<Integer>());

		System.out.println("등록된 학생: 한지운, 김하진, 하여린, 윤단비 등 4명입니다.");
		while(true) { 
			System.out.print("이름과 점수들>>");
			String line = scanner.nextLine(); 
			if(line.equals("그만"))
				break;		
			String [] tokens = line.split(" "); 
			String name = tokens[0]; 
			Vector<Integer> v = map.get(name); // 해시맵에서 name의 키를 가진 벡터 읽기
			if(v == null) { 
				System.out.println(name + "은 없는 학생입니다.");
				continue;			
			}
			// 이름이 해시맵에 있는 경우 벡터 v에 점수 삽입
			for(int i=0; i<tokens.length - 1; i++)
				v.add(Integer.parseInt(tokens[i+1])); 
		}		
		while(true) { 
			System.out.print("검색할 이름>>");
			String name = scanner.next(); 
			if(name.equals("그만"))
				break;
			Vector<Integer> v = map.get(name); 
			if(v == null) { 
				System.out.println(name + "은 없는 학생입니다.");
				continue;
			}
			if(v.size() == 0) { 
				System.out.println(name + "은 토익 점수가 없습니다.");
				continue;
			}
			for(int score : v) // for(int i=0; i<v.size(); i++) int score = v.get(i)와 동일
				System.out.print(score + " ");
			System.out.println();
		}
		scanner.close();
	}
}

