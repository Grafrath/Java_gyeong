package ex_interface;

public class OrderService {
	
	
	private OrderRepository repository;
	
	public OrderService(OrderRepository repository) {
        this.repository = repository;
    }
	
	void createOrder() {
		repository.save();
	}
	
	//프론트에서 넘어온 주문내역을 MySQL에 저장하기 위해 save() 메서드 호출
	//나중에 OracleOrderRepository로 바꾸고 싶으면 OrderService를 고쳐야함.
	
}
