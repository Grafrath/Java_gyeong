package d2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.google.common.reflect.ClassPath;

@Component class Car{};
@Component class SportCar extends Car{};
@Component class Truck extends Car{};
@Component class Engine {};

class AppContext{
	Map map;
	
	public AppContext() {
		map = new HashMap();
		doComponentScan();
	}

	private void doComponentScan() {
		//1. 패키지내의 클래스 목록을 가져온다.
		//2. 반복문으로 클래스를 하나씩 읽어와서 @Component가 붙어있는지 확인
		//3. @Component가 붙어있으면 객체를 생성해서 map에 저장
		try {
			ClassLoader classLoader = AppContext.class.getClassLoader(); 
			//AppContext 클래스의 클래스 로더. JVM에서 클래스를 동적으로 로드하고,
			//애플리케이션이 사용할 수 있도록 메모리에 적재하는 역할
			
			ClassPath classPath = ClassPath.from(classLoader);
			//ClassPath 객체를 생성하여 지정된 클래스 로더에서 클래스 경로 확인.
			//ClassPath는 구아바(Guava) 라이브러리에서 제공하는 기능,
			//클래스 경로 상의 모든 클래스를 탐색하고 사용할 수 있게 해줌.
			
			Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("d2");
			//지정한 패키지내의 최상위 클래스들(탑 레벨 클래스)을 가져온다. 
			//그 결과로 ClassPath.ClassInfo 객체들의 집합(Set)을 반환한다.
			
			for(ClassPath.ClassInfo classInfo : set) {
				//위에서 얻은 클래스 정보를 반복 처리. 각 ClassPath.ClassInfo 객체는 특정 클래스에 대한 정보입력.
				
				Class clazz = classInfo.load();
				//현재 ClassInfo 객체를 실제로 로드된 클래스(Class)로 변환.
				//해당 클래스의 정보를 기반으로 JVM에서 해당 클래스를 로드하여 Class 객체를 반환.
				
				Component component = (Component)clazz.getAnnotation(Component.class);
				//해당 클래스에 @Component 애노테이션이 있는지 확인
				
				if(component != null) {
					//해당 클래스가 @Component로 지정된 클래스라면 아래의 로직을 실행.
					
					String id = StringUtils.uncapitalize(classInfo.getSimpleName());
					//클래스 이름의 첫 글자를 소문자로 변환하여 id로 사용
					
					map.put(id, clazz.newInstance());
				}
			}			
		} catch (Exception e) {
			
		}
	}
	
	Object getBean(String key) {
		return map.get(key);
	}
}

public class Main3 {

	public static void main(String[] args) {
		
		AppContext ac = new AppContext();
		
		Car car = (Car)ac.getBean("car");
		System.out.println("car3= " + car);
		
		SportCar sport = (SportCar)ac.getBean("SportCar");
		System.out.println("SportCar= " + sport);
		
		Truck truck = (Truck)ac.getBean("Truck");
		System.out.println("Truck= " + truck);
		
		Engine engine = (Engine)ac.getBean("engine");
		System.out.println("engine= " + engine);
		
	}

}
