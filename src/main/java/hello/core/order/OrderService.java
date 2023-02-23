package hello.core.order;

// 주문 서비스 역할의 Interface.

public interface OrderService {

    Order createOrder(Long memberId, String itemName, int itemPrice);
}
