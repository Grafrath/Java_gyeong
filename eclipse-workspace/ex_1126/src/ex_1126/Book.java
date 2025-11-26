package ex_1126;

public class Book {
	
	String title;
	String author;
	
	Book (String t, String a) {
		this.title = t;
		this.author = a;
	}
	
	void printInfo() {
		System.out.printf("제목 : %s, 저자 : %s\n", title, author);
	}
	
}
