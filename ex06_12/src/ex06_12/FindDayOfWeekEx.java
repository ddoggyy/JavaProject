package ex06_12;

import java.util.*;

public class FindDayOfWeekEx {
	public static String findDayOfWeek(int year, int month, int dayOfMonth) {
		Calendar cal = Calendar.getInstance();
		
		if(month < 1 || month > 12) 
			return "입력 오류! 달의 범위는 1~12입니다.";
		
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		if(dayOfMonth > maxDay) 
			return "입력 오류! " + year + "년 " + month + "월은 " + maxDay + "일까지입니다.";
		
		cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		String res = "";
		switch(dayOfWeek) {
		case Calendar.SUNDAY : res = "일요일"; break;
		case Calendar.MONDAY : res = "월요일"; break;
		case Calendar.TUESDAY : res = "화요일"; break;
		case Calendar.WEDNESDAY : res = "수요일"; break;
		case Calendar.THURSDAY : res = "목요일"; break;
		case Calendar.FRIDAY : res = "금요일"; break;
		case Calendar.SATURDAY : res = "토요일"; break;
		}
		return res;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.print("년 월 일 입력>>");
			String line = scanner.nextLine();
			if(line.equals("그만")) break;
			StringTokenizer st = new StringTokenizer(line, " ");
			int year = Integer.parseInt(st.nextToken().trim());
			int month = Integer.parseInt(st.nextToken().trim());
			int dayOfMonth = Integer.parseInt(st.nextToken().trim());
			String dayOfWeek = findDayOfWeek(year, month, dayOfMonth);
			System.out.println(dayOfWeek);
		}
		scanner.close();
	}
}
