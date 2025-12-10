package exam;

public class FilePrinter implements Printable {
	public void print(String message) {
		System.out.printf("파일에 출력: [%s]\n",message);
	}
}