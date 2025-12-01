package ex_interface;

public class ReportService implements Printable, Exportable {

	@Override
	public void export() {
		// TODO Auto-generated method stub
		System.out.println("리포트를 파일로 내보낸다.");
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println("리포트를 출력한다.");
	}

}
