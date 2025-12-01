package ex_interface;

public class OracleOrderRepository implements OrderRepository {
	
	public void save() {
		System.out.println("Oracle 주문내역 저장.");		
	}
	
}
