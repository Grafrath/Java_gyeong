package ex_interface;

public class MYSQLOrderRepository implements OrderRepository {

	public void save() {
		System.out.println("MySQL 주문내역 저장.");		
	}

}
