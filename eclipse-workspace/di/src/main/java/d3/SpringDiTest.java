package d3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDiTest {

	public static void main(String[] args) {
		
		ApplicationContext ac = new AnnotationConfigApplicationContext("d3");
		
		Car car = (Car)ac.getBean("car");
		Engine engine = (Engine)ac.getBean(Engine.class);
		Door door = (Door)ac.getBean(Door.class);
		
		System.out.println("car4= " + car);
		System.out.println("engine=" + engine);
		System.out.println("door="+door);
		
	}

}
