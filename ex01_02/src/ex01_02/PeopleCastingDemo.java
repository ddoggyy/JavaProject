package ex01_02;

class Person {
	protected String name;
	Person(String name) {
		this.name = name;
	}
	void showInfo() {
		
	}
}

class Student extends Person {
	 private String major;
	 Student(String name, String major) {
		 super(name);
		 this.name = name;
		 this.major = major;
	 }
	 void showInfo() {
		 System.out.println("Student: " + name + " / 전공: " + major);
	 }
	 void getMajor() {
		 System.out.println(major);
	 }
}

class Professor extends Person {
	private String department;
	Professor(String name, String department){
		super(name);
		this.name = name;
		this.department = department;
	}
	void showInfo() {
		System.out.println("Professor: " + name + " / 학과: " + department);
	}
	void getDepartment() {
		System.out.println(department);
	}
}

public class PeopleCastingDemo {
    public static void main(String[] args) {
    	// 1) people이라는 객체배열을 업캐스팅으로 선언. 이때 3개의 배열이 내부에 들어있어야 함.
        //    Student("김철수", "Computer"),
        //    Professor("이영희", "AI Engineering"),
        //    Student("홍길동", "Software")

    	Person people[] = { new Student("김철수", "Computer"),
    	        			new Professor("이영희", "AI Engineering"),
    	        			new Student("홍길동", "Software") };    	

        // 2) people 객체를 활용하여 foreach 구문을 구현. showInfo()라는 메서드를 호출 할 것.
    	// 3) 또한, 각 객체를 다운캐스팅하여, 
        // 학생일 경우 : 다음을 뜨도록 함 "-> 전공 확인: " 문구와 getMajor() 메서드 호출
        // 교수일 경우 : 다음을 뜨도록 함 "-> 소속 학과 확인: " 문구와 getDepartment() 메서드 호출
    	
    	for(Person i : people) {
    		i.showInfo();
    		if(i instanceof Student) {
    			System.out.print("전공 확인: ");
    			((Student)i).getMajor();
    		}
    		else if(i instanceof Professor) {
    			System.out.print("소속 학과 확인: ");
    			((Professor)i).getDepartment();
    		}
    	}
    }
}

