import { orderApi } from './api.js';
import * as UI from './ui.js';

export async function createOrder(productId, quantity) {
    try {
        const order = await orderApi.createOrder(productId, quantity);
        alert(`주문 성공! [${order.productName}] ${order.quantity}개, 총 ${order.orderTotalPrices.toLocaleString()}원.`);

        // 성공 시, 호출한 곳(script.js)에서 재고 및 주문 목록을 새로고침합니다.
        return order;
    } catch (error) {
        console.error("주문 생성 오류:", error);
        // OrderService에서 발생한 재고 부족, 유효성 검증 오류 메시지를 사용자에게 표시
        // script.js에서 이 에러를 catch하여 alert합니다.
        throw new Error(error.message);
    }
}

// 주문 목록 조회링
export async function loadOrders() {
    try {
        const orders = await orderApi.loadOrders();
        UI.renderOrderList(orders);
    } catch (error) {
        console.error("주문 목록 로드 오류:", error);
        UI.renderOrderList([]); // 오류 시 목록 비우기
        UI.showErrorStatus(error.message); // 에러 상태 메시지 표시
    }
}