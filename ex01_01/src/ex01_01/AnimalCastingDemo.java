package ex01_01;

class Animal {
    void speak() { System.out.println("..."); }
}

class Dog extends Animal {
	void speak() { System.out.println("멍멍!"); }
	static void bark() { System.out.println("Dog 전용: bark() 호출"); }
}

class Cat extends Animal {
	void speak() { System.out.println("야옹~"); }
}

class Tiger extends Animal {
	void speak() { System.out.println("어흥!"); }
}

public class AnimalCastingDemo {
   public static void main(String[] args) {    
       // 업캐스팅
	   // 1) zoo라는 객체배열을 업캐스팅으로 선언하시오. 이때 배열은 new Dog(), new Cat(), new Tiger(), new Dog()를 가져야 한다. 
	   Animal zoo[] = { new Dog(), new Cat(), new Tiger(), new Dog() };
   
       // 2) zoo를 활용해 foreach 구문을 돌며, 각자의 speak()를 호출한다. 단, "Dog"클래스의 경우에만 다운캐스팅 하여 bark를 호출한다. 
	   for(Animal i : zoo) {
		   i.speak();
		   if (i instanceof Dog) { 
			   ((Dog) i).bark();
		   }
	   }   
        // 잘못된 다운캐스팅 방지 예시
        Animal justCat = new Cat();
        if (justCat instanceof Dog) {
            Dog d = (Dog) justCat; // 실행되지 않음
            d.bark();
        } 
        else System.out.println("Cat -> Dog 다운캐스팅 불가(안전 차단)");
   }
}

