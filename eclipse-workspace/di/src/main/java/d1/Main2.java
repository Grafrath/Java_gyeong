package d1;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

//객체 컨테이너 클래스 안쪽에서 Map을 이용해 객체를 저장한다.
//ApplicationContext

class AppnContext{
	Map map;
	
	public AppnContext() {
		try {
			Properties p = new Properties();
			p.load(new FileReader("config.txt"));
			
			map = new HashMap(p);
			
			for(Object key : map.keySet()) {
				Class clazz = Class.forName((String)map.get(key));
				map.put(key, clazz.newInstance());
			}
		} catch (Exception e) {
			
		}
	}
	
	Object getBean(String key) {
		return map.get(key);
	}
}

public class Main2 {

	public static void main(String[] args) throws Exception {
		
		AppnContext ac = new AppnContext();
		
		Car car = (Car)ac.getBean("car");
		System.out.println("car2= " + car);
		
		SportCar sport = (SportCar)ac.getBean("SportCar");
		System.out.println("SportCar= " + sport);
		
		Truck truck = (Truck)ac.getBean("Truck");
		System.out.println("Truck= " + truck);
		
		Engine engine = (Engine)ac.getBean("engine");
		System.out.println("engine= " + engine);
		
	}

}
