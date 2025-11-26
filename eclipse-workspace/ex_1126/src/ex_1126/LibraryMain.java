package ex_1126;

public class LibraryMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Library l1 = new Library(3);
		
		l1.addBook("반지의제왕", "톨킨");
		l1.addBook("해리포터", "조앤롤링");
		l1.addBook("정의란 무엇인가", "마이클");
		l1.addBook("테스트", "테스트");
		
		l1.printAllBooks();

	}

}

/*

public class LibraryMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Library l1 = new Library(3);
		
		l1.addBook("반지의제왕", "톨킨");
		l1.addBook("해리포터", "조앤롤링");
		l1.addBook("정의란 무엇인가", "마이클");
		l1.addBook("테스트", "테스트");
		
		l1.printAllBooks();

	}

}

*/

/*

import java.util.*;

public class Library {
	
	Book[] books;
	int count;
	
	Library (int n) {
		books = new Book[n];
		this.count = 0;
	}
	
	void addBook(String title, String author) {
		if (count == books.length) { 
			System.out.println("더 이상 책을 추가할 수 없습니다.");
		} else {
			books[count] = new Book(title, author);
			count++;
		}
	}
	
	void printAllBooks() {
		
		System.out.println("=== 도서 목록 ===");
		
		for (int i = 0; i < count; i++) {
			books[i].printInfo();
		}
	}

}

*/

/*

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

*/