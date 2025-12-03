package ex05_05;

import ex05_05.Circle;
import ex05_05.Line;
import ex05_05.Rect;
import ex05_05.Shape;

class Shape {
	public Shape next;
	public Shape() { next = null; }
	
	public void draw() {
		System.out.println("Shape");
	}
}

class Line extends Shape {
	public void draw() {
		System.out.println("Line");
	}
}

class Rect extends Shape {
	public void draw() {
		System.out.println("Rect");
	}
}

class Circle extends Shape {
	public void draw() {
		System.out.println("Circle");
	}
}

public class UsingOverride {
	public static void main(String[] args) {
		Shape start, last, obj; // Linked List로 도형 생성하여 연결
		start = new Line();
		last = start;
		obj = new Rect();
		last.next = obj;
		last = obj;
		obj = new Line();
		last.next = obj;
		last = obj;
		obj = new Circle();
		last.next = obj;
		
		Shape p = start;
		while(p != null) {
			p.draw();
			p = p.next;
		}
		
	}
}
