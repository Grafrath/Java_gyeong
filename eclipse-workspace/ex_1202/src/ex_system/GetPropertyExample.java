package ex_system;

public class GetPropertyExample {

	public static void main(String[] args) {
		
		String osName = System.getProperty("os.name");
		String userName = System.getProperty("user.name");
		String userHome = System.getProperty("user.home");
		System.out.println(osName);
		System.out.println(userName);
		System.out.println(userHome);
		
		//os별 파일경로 다르게 처리
		//특정 os환경에서만 기능 활성화
		//JVM 버전 체크
		//파일저장위치를 사용자 환경에 따라 자동설정
		
	}

}
