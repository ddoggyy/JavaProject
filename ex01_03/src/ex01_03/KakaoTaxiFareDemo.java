package ex01_03;

//인터페이스 (수정금지)
interface Payable { void pay(int amount); }
interface Discountable { int getDiscountRate(); }        // 일반 할인율 %
interface EcoDiscountable { int getEcoDiscountRate(); }  // 전기차 친환경 할인율 %
interface Surchargable { int getSurcharge(); }           // 추가요금(원)

//택시 공통 추상 클래스  (수정금지)
abstract class Taxi implements Payable {
	protected String name;
	Taxi(String name) { this.name = name; }
	// 공통 요금 규칙
	protected int baseFare() { return 3800; }                  // 기본요금
	protected int extraFare(int distanceKm) {                  // 5km 초과분 200원/km
	   return (distanceKm > 5) ? (distanceKm - 5) * 200 : 0;
	}
	// 최종 요금 계산(자식에서 raw 합산 후 호출)
	protected int applyAdjustments(int rawTotal) {
	   int total = rawTotal;
	   // 추가요금
	   if (this instanceof Surchargable) {
	       total += ((Surchargable) this).getSurcharge();
	   }
	   // 할인율 합산 (일반 + 친환경, 상한 50%)
	   int discountSum = 0;
	   if (this instanceof Discountable) discountSum += ((Discountable) this).getDiscountRate();
	   if (this instanceof EcoDiscountable) discountSum += ((EcoDiscountable) this).getEcoDiscountRate();
	   if (discountSum > 50) discountSum = 50;
	
	   int discount = total * discountSum / 100;
	   return total - discount;
	}
	// 각 택시별 최종 요금 계산
	abstract int calculateFare(int distanceKm);
	@Override
	public void pay(int amount) {
	   System.out.println("[" + name + "] " + amount + "원 결제 완료");
	}
}

//1) 카카오 기본택시(할인 없음)
class KakaoTaxi extends Taxi implements Discountable {
	KakaoTaxi(String name) {
		super(name);
		this.name = name;
	}
	@Override
	public int getDiscountRate() {
		return 0;
	}
	int calculateFare(int distanceKm) {
		return applyAdjustments(baseFare() + extraFare(distanceKm));
	}
}

//2) 카카오블랙(추가요금 1000원)
class KakaoBlack extends Taxi implements Surchargable, Discountable {
	KakaoBlack(String name) {
		super(name);
		this.name = name;
	}
	@Override
	public int getSurcharge() {
		return 1000;
	}
	@Override
	public int getDiscountRate() {
		return 0;
	}
	int calculateFare(int distanceKm) {
		return applyAdjustments(baseFare() + extraFare(distanceKm));
	}	
}

//3) 카카오EV(전기차 5% 할인)
class KakaoEV extends Taxi implements EcoDiscountable, Discountable {
	KakaoEV(String name) {
		super(name);
		this.name = name;
	}
	@Override
	public int getDiscountRate() {
		return 0;
	}
	@Override
	public int getEcoDiscountRate() {
		return 5;
	}
	int calculateFare(int distanceKm) {
		return applyAdjustments(baseFare() + extraFare(distanceKm));
	}
}

//4) 카카오블랙EV(블랙 +1000, EV 5% 할인)
class KakaoBlackEV extends Taxi implements Surchargable, EcoDiscountable, Discountable {
	KakaoBlackEV(String name) {
		super(name);
		this.name = name;
	}
	@Override
	public int getDiscountRate() {
		return 0;
	}
	@Override
	public int getEcoDiscountRate() {
		return 5;
	}
	@Override
	public int getSurcharge() {
		return 1000;
	}
	int calculateFare(int distanceKm) {
		return applyAdjustments(baseFare() + extraFare(distanceKm));
	}
}


//5) 카카오블랙EV(블랙 +1000, EV 5% 할인)
class DiscountKakaoTaxi extends Taxi implements Discountable {
	private int dis;
	DiscountKakaoTaxi(String name, int discountSum) {
		super(name);
		this.name = name;
		dis = discountSum;
	}
	public int getDiscountRate() {
		return dis;
	}
	int calculateFare(int distanceKm) {
		return applyAdjustments(baseFare() + extraFare(distanceKm));
	}
	
}


public class KakaoTaxiFareDemo {
public static void main(String[] args) {
   int distance = 8; // km

   Taxi[] list = new Taxi[] {
       new KakaoTaxi("카카오택시-일반"),
       new KakaoBlack("카카오블랙"),
       new KakaoEV("카카오EV"),
       new KakaoBlackEV("카카오블랙EV"),
       new DiscountKakaoTaxi("카카오택시-할인쿠폰", 5)
   };

   for (Taxi t : list) {
       int fare = t.calculateFare(distance);
       System.out.println("이동거리: " + distance + "km, [" + t.name + "] 최종요금 = " + fare + "원");
       t.pay(fare);
   }
}
}

