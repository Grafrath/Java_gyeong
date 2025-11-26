package ex_1126;
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