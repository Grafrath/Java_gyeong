package inner_class;

import inner_class.Button.OnClickListener;

public class Main {

	public static void main(String[] args) {
		
		Outer Out = new Outer();
		
		//내부클래스 객체
		Outer.Inner inn = Out.new Inner();
		inn.print();
		
		System.out.println();
		
		RemoteControl rc = new RemoteControl() {
			@Override
			public void turnOn() {
				System.out.println("TV를 켭니다.");
			}
			@Override
			public void turnOff() {
				System.out.println("TV를 끕니다.");
			}
		};
		
		rc.turnOn();
		rc.turnOff();
		
		System.out.println();
		
		Calculator cal = new Calculator() {
			
			public int calc(int x) {
				return x*x;				
			}
			
		};
		
		System.out.println(cal.calc(5));
		System.out.println(cal.calc(3));
		
		System.out.println();
		
		Button bt = new Button();
		
		bt.setOnClickListener(new OnClickListener() {
			public void OnClick() {
				System.out.println("버튼이 클릭되었습니다.");
			}
		});
		
	}

}
