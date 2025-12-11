import { orderApi } from './orderApi.js';
import { renderOrderList, orderListBody } from './orderUi.js';
import * as UI from './ui.js';

// 1. 주문 목록 조회 및 렌더링
export async function loadOrders() {
    try {
        UI.showLoadingStatus();
        const orders = await orderApi.loadOrders();
        renderOrderList(orders);
    } catch (error) {
        UI.showErrorStatus(error.message);
    }
}

// 2. 주문 등록 함수 (상품 목록에서 호출될 예정)
export async function createOrder(productId, quantity) {
    if (quantity <= 0) {
        alert("주문 수량은 1개 이상이어야 합니다.");
        return;
    }

    if (confirm(`상품 ID [${productId}]를 ${quantity}개 주문하시겠습니까?`)) {
        try {
            const orderRequest = { productId, quantity };
            
            await orderApi.createOrder(orderRequest);
            alert("✅ 주문이 성공적으로 처리되었습니다. 재고가 차감되었습니다.");
            
            loadOrders();

        } catch (error) {
            console.error("주문 실패:", error);
            alert(`❌ 주문 실패: ${error.message}`);
        }
    }
}

document.addEventListener("DOMContentLoaded", () => {
    loadOrders();
});