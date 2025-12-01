package Exam;

public class DocumentProcessor implements Printable, Sortable {

	@Override
	public void sort() {
		System.out.println("문서 정렬");		
	}

	@Override
	public void print() {
		System.out.println("문서 출력");		
	}

}
